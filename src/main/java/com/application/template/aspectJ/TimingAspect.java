package com.application.template.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.application.template.aspectJ.annotation.TimeCount;

@Aspect
@Component
public class TimingAspect {

    private static final Logger logger = LoggerFactory.getLogger(TimingAspect.class);


    @Pointcut("@annotation(com.application.template.aspectJ.annotation.TimeCount)")
    public void getPointCut() {}

    @Around("getPointCut()")
    public Object calRunningTime(ProceedingJoinPoint point) throws Throwable {
        String countName = getCountName(point);
        long before = System.currentTimeMillis();
        logger.info("计时方法开始:{}, 当前系统时间:{}", countName, before);
        Object proceed = point.proceed(point.getArgs());
        long after = System.currentTimeMillis();
        long runningCost = after - before;
        logger.info("计时方法结束:{}, 当前系统时间:{}, 总用时:{}", countName, after, runningCost);
        return proceed;
    }

    private String getCountName(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        TimeCount count = signature.getMethod().getAnnotation(TimeCount.class);
        return count.name();
    }
}
