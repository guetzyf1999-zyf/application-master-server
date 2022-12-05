package com.application.template.service.authService.jwtservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwtauth.secretkey}")
    private String secretKey;

    @Override
    public String sing(String username,String telephone) {
        Map<String, String> payload = genUserPayload(username, telephone);
        return JWT.create().withPayload(payload).sign(Algorithm.HMAC256(secretKey));
    }

    public Map<String,String> genUserPayload(String username, String telephone) {
        HashMap<String, String> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("telephone", telephone);
        return payload;
    }
}
