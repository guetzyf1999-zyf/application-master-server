package com.application.template.entity.appUser;

import org.springframework.security.core.GrantedAuthority;

public class AppUserAuthorities implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
