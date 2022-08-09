package com.application.template.service.authService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwtauth.secretkey}")
    private String secretKey;

    @Value("${jwtauth.expiretime}")
    private String expireTime;

    @Override
    public String sing(String username,String telephone) {
        Date expiresAt = new Date(System.currentTimeMillis() + expireTime);
        return null;
    }
}
