package com.application.template.service.authentication.userloadserivce;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.template.dto.auth.LoginAuthBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.UserLoadServiceFactory;
import com.application.template.service.appUser.AppUserService;

@Service
@Transactional
public class UserLoadByEmailServiceImpl implements UserLoadService, InitializingBean {

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(LoginAuthBody loginAuthBody) {
        return appUserService.getUserByEmail(loginAuthBody.getVerifyCredentials());
    }

    @Override
    public void afterPropertiesSet(){
        UserLoadServiceFactory.putMessageService(LoginAuthWay.EMAIL, this);
    }
}
