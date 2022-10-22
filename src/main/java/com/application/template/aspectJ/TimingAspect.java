package com.application.template.aspectJ;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class TimingAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimingAspect.class);

    private static final ThreadLocal<Map<String, Long>> START_TIME = new ThreadLocal<>();


    @Pointcut("@annotation(com.application.template.aspectJ.annotation.TimeCount)")
    public void getPointCut() {}

    @Before("getPointCut()")
    public void getStartTime(JoinPoint point) {
        String countName = getCountName(point);
        long before = System.currentTimeMillis();
        setCountStartTime(countName);
        logger.info("计时方法开始:{}, 当前系统时间:{}", countName, before);
    }

    @After("getPointCut()")
    public void calRunningTime(JoinPoint point){
        long after = System.currentTimeMillis();
        getEndTimeAndRemove(getCountName(point), after);
    }

    private void setCountStartTime(String countName) {
        Map<String, Long> timeMap = START_TIME.get();
        boolean hasTimeMap = timeMap != null;
        timeMap = hasTimeMap ? timeMap : new HashMap<>();
        boolean hasSameName = timeMap.containsKey(countName);
        AppAssert.judge(hasSameName, new AppException("同一个调用栈内存在相同得计时名:" + countName));
        timeMap.put(countName, System.currentTimeMillis());
        if (!hasTimeMap) START_TIME.set(timeMap);
    }

    private void getEndTimeAndRemove(String countName, long after) {
        Map<String, Long> timeMap = START_TIME.get();
        try {
            Long before = timeMap.get(countName);
            long runningCost = after - before;
            logger.info("计时方法结束:{}, 当前系统时间:{}, 总用时:{}", countName, after, runningCost);
            timeMap.remove(countName);
        }finally {
            if (timeMap.isEmpty()) START_TIME.remove();
        }
    }

    private String getCountName(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        TimeCount count = signature.getMethod().getAnnotation(TimeCount.class);
        return count.name();
    }
}
