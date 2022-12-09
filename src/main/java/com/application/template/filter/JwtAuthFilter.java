package com.application.template.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.application.template.service.appUser.AppUserService;
import com.application.template.service.authentication.jwtservice.JwtService;
import com.auth0.jwt.interfaces.Claim;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
              @NotNull FilterChain filterChain) throws IOException, ServletException {
        String jwtToken = request.getHeader("Authorization");
        if (StringUtils.hasText(jwtToken)) verifySignature(jwtToken, request);
        filterChain.doFilter(request, response);
    }

    private void verifySignature(String jwtToken,HttpServletRequest request) {
        String token = jwtToken.replace("Bearer ", "");
        Map<String, Claim> jwtClaim = jwtService.decodedJwt(token);
        String phone = jwtClaim.get("telephone").asString();
        UserDetails userDetails = appUserService.getUserByTelephone(phone);
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken
                (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
