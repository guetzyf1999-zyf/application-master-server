package com.application.template.service.appUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.application.template.dto.login.RegisterBody;
import com.application.template.entity.appUser.AppUser;
import com.application.template.enumtype.CaptchaKeyPrefix;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppUserException;
import com.application.template.mapper.appUser.AppUserMapper;
import com.application.template.service.authentication.UserAuthenticationService;

@Service
public class AppUserServiceImpl implements AppUserService {

	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Autowired
	private AppUserMapper userMapper;

	@Autowired
	private UserAuthenticationService authenticationService;

	@Override
	public AppUser register(RegisterBody registerBody, String captchaKey) {
		CaptchaKeyPrefix prefix = CaptchaKeyPrefix.getCaptchaKeyPrefixByIndex(registerBody.getCaptchaKeyPrefix());
		authenticationService.checkCaptchaCodeAndDelete(prefix.getPrefix() + captchaKey, registerBody.getCaptchaCode());
		checkUserInfoLegal(registerBody);
		AppUser user = new AppUser(registerBody);
		userMapper.insert(user);
		user.setUsername(userMapper.findUsernameByEmail(user.getEmail()));
		logger.info("新用户注册成功{}", registerBody.getNickname() + registerBody.getPhoneNumber());
		return user;
	}

	@Override
    public AppUser getUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
    }

	@Override
	public AppUser getUserByTelephone(String phone) {
		return userMapper.findUserByTelephone(phone);
	}

	@Override
	public AppUser getUserByEmail(String email) {
		return userMapper.findUserByEmail(email);
	}

	@Override
	public String getEmailByUsername(String username) {
		return userMapper.findEmailByUsername(username);
	}

	private void checkUserInfoLegal(RegisterBody registerBody) {
		boolean emailHasBeenUsed = userMapper.findIdByEmail(registerBody.getEmail()) != null;
		AppAssert.judge(emailHasBeenUsed, new AppUserException("该邮箱已被注册"));
		boolean phoneHasBeenUsed = userMapper.findIdByTelephone(registerBody.getPhoneNumber()) != null;
		AppAssert.judge(phoneHasBeenUsed, new AppUserException("该电话已被注册"));
	}

	/*
	* 参数verifyId可以是电话也可以是邮箱也可以是账号
	* */
	@Override
	public UserDetails loadUserByUsername(String verifyId) {
		AppUser user = getUserByTelephone(verifyId);
		user = user != null ? user : getUserByEmail(verifyId);
		user = user != null ? user : getUserByUsername(verifyId);
		return user;
	}
}
