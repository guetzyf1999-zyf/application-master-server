package com.application.template.entity.menu;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("application_info")
public class ApplicationInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String applicationName;
    private String applicationSeq;
    private String applicationDescribe;
    private List<ApplicationServiceInfo> applicationServiceInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationSeq() {
        return applicationSeq;
    }

    public void setApplicationSeq(String applicationSeq) {
        this.applicationSeq = applicationSeq;
    }

    public String getApplicationDescribe() {
        return applicationDescribe;
    }

    public void setApplicationDescribe(String applicationDescribe) {
        this.applicationDescribe = applicationDescribe;
    }

    public List<ApplicationServiceInfo> getApplicationServiceInfos() {
        return applicationServiceInfos;
    }

    public void setApplicationServiceInfos(List<ApplicationServiceInfo> applicationServiceInfos) {
        this.applicationServiceInfos = applicationServiceInfos;
    }
}
