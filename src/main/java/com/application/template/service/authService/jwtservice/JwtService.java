package com.application.template.service.authService.jwtservice;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public interface JwtService {

    String sing(String username,String telephone);

    Map<String, Claim> decodedJwt(String token);
}
