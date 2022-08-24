package com.application.template.entity.appUser;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("app_user_affiliated_organization")
public class AffiliatedOrganization implements Serializable {
//    private Integer appUserId;
    private String organizationSeq;
    private boolean inUse;

    public AffiliatedOrganization(String organizationSeq, boolean inUse) {
        this.organizationSeq = organizationSeq;
        this.inUse = inUse;
    }

    public String getOrganizationSeq() {
        return organizationSeq;
    }

    public void setOrganizationSeq(String organizationSeq) {
        this.organizationSeq = organizationSeq;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
