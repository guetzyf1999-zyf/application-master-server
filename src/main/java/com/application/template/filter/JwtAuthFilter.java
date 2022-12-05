package com.application.template.filter;

import com.application.template.dto.auth.AuthBody;
import com.application.template.service.authService.jwtservice.JwtService;
import com.application.template.util.JsonUtil;
import com.auth0.jwt.interfaces.Claim;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService authenticationService;

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
        UserDetails userDetails = authenticationService.loadUserByUsername((JsonUtil.toJson(new AuthBody(phone))));
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken
                (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
