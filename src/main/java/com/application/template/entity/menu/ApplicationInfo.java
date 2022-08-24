package com.application.template.entity.menu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("application_info")
public class ApplicationInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String applicationName;
    private String applicationSeq;
    private String applicationDescribe;
    List<ApplicationServiceInfo> applicationServiceInfos;
}
