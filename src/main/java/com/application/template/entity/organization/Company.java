package com.application.template.entity.organization;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("Company")
public class Company {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String companyNo;
}
