package com.application.template.service.authService.userloadserivce;

import com.application.template.dto.auth.AuthBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.UserLoadServiceFactory;
import com.application.template.service.appUser.AppUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLoadByEmailServiceImpl implements UserLoadService, InitializingBean {

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(AuthBody authBody) {
        return appUserService.getUserByEmail(authBody.getVerifyCredentials());
    }

    @Override
    public void afterPropertiesSet(){
        UserLoadServiceFactory.putMessageService(LoginAuthWay.EMAIL, this);
    }
}
