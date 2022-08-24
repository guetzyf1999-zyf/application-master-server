package com.application.template.entity.organization;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("organization")
public class Organization {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String organizationSeq;
    private String organizationDescribe;
    private String creator;
    private String creatorUsername;
    private String createTime;
    private String owner;
    private String ownerUsername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationSeq() {
        return organizationSeq;
    }

    public void setOrganizationSeq(String organizationSeq) {
        this.organizationSeq = organizationSeq;
    }

    public String getOrganizationDescribe() {
        return organizationDescribe;
    }

    public void setOrganizationDescribe(String organizationDescribe) {
        this.organizationDescribe = organizationDescribe;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
