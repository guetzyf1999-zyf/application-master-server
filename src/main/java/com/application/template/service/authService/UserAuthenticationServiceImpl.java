package com.application.template.service.authService;

import com.application.template.dto.auth.AuthBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.UserLoadServiceFactory;
import com.application.template.util.JsonUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public UserDetails loadUserByUsername(String authJson) {
        AuthBody authBody = JsonUtil.toObject(authJson, AuthBody.class);
        return UserLoadServiceFactory.getUserLoadService(LoginAuthWay.getLoginAuthWayByIndex(authBody.getAuthWay()))
                .loadUserByUsername(authBody);
    }
}
