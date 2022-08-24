package com.application.template.dto.auth;

import com.application.template.entity.appUser.AppUser;

public class AuthSuccessBody {
    private AppUser user;
    private String jwtToken;

    public AuthSuccessBody(AppUser user, String jwtToken) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
