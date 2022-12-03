package com.application.template.service.appUser;

import java.util.Date;
import java.util.Random;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.dto.auth.AuthBody;
import com.application.template.dto.login.CaptchaAuthAccessWay;
import com.application.template.dto.login.CaptchaAuthDTO;
import com.application.template.dto.login.JwtAuthResponseBody;
import com.application.template.dto.login.RegisterBody;
import com.application.template.enumtype.LoginAuthWay;
import com.application.template.factory.MessageSendingServiceFactory;
import com.application.template.service.authService.JwtService;
import com.application.template.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.application.template.entity.appUser.AppUser;
import com.application.template.enumtype.AppUserAuthExceptionHandle;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppUserException;
import com.application.template.mapper.appUser.AppUserMapper;
import com.application.template.service.message.MessageService;
import com.application.template.util.SpringUtil;

@Service
public class AppUserServiceImpl implements AppUserService {

	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Value("${captcha.effectivetime}")
	private long effectiveTime;

	@Value("${captcha.messagetemplate}")
	private String messageTemplate;

	@Autowired
	private AppUserMapper userMapper;

	@Autowired
	private JwtService jwtService;

	@Override
	public JwtAuthResponseBody login(AuthBody authBody) {
		try {
			String authJson = JsonUtil.toJson(authBody);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authJson,
					authBody.getPassword());
			Authentication authenticate = SpringUtil.getBean(AuthenticationManager.class).authenticate(authenticationToken);
			AppUser user = (AppUser) authenticate.getPrincipal();
			user.setEmail(userMapper.findEmailByUsername(user.getUsername()));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			String jwtToken = jwtService.sing(user.getUsername(), user.getTelephone());
			return new JwtAuthResponseBody(user, jwtToken);
		} catch (Exception exception) {
			String handleMessage = handleAuthUserException(exception);
			throw new AppUserException(handleMessage);
		}
	}

	@Override
	@TimeCount(name = "getCaptchaCode")
	public CaptchaAuthDTO getCaptchaCode(CaptchaAuthAccessWay accessWay) {
		MessageSendingApproach approach = MessageSendingApproach.valueOf(accessWay.getAuthWay());
		String captcha = String.valueOf(new Random().nextInt(999999) % (999999 - 100000 + 1) + 100000);
		MessageService messageService = MessageSendingServiceFactory.getMessageService(approach);
		messageService.sendCaptchaMessage(accessWay, genMessageTemplate(), captcha);
		return new CaptchaAuthDTO(captcha, effectiveTime);
	}

	@Override
	public AppUser register(RegisterBody registerBody) {
		checkUserInfoLegal(registerBody);
		AppUser user = createAppUser(registerBody);
		userMapper.insert(user);
		user.setUsername(userMapper.findUsernameByEmail(user.getEmail()));
		logger.info("新用户注册成功{}", registerBody.getNickname() + registerBody.getPhoneNumber());
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String authJson) {
		AuthBody authBody = JsonUtil.toObject(authJson, AuthBody.class);
		if (authBody.getAuthWay().equals(LoginAuthWay.USERNAME.getIndex())) {
			return userMapper.findUserByUsername(authBody.getVerifyCredentials());
		} else if (authBody.getAuthWay().equals(LoginAuthWay.PHONE.getIndex())) {
			return userMapper.findUserByTelephone(authBody.getVerifyCredentials());
		} else if (authBody.getAuthWay().equals(LoginAuthWay.EMAIL.getIndex())) {
			return userMapper.findUserByEmail(authBody.getVerifyCredentials());
		}
		return null;
	}

	private AppUser createAppUser(RegisterBody registerBody) {
		AppUser user = new AppUser();
		String hashedPassword = BCrypt.hashpw(registerBody.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);
		user.setNickName(registerBody.getNickname());
		user.setTelephone(registerBody.getPhoneNumber());
		user.setRegisterDate(new Date());
		user.setEmail(registerBody.getEmail());
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		return user;
	}

	private void checkUserInfoLegal(RegisterBody registerBody) {
		boolean emailHasBeenUsed = userMapper.findIdByEmail(registerBody.getEmail()) != null;
		AppAssert.judge(emailHasBeenUsed, new AppUserException("该邮箱已被注册"));
		boolean phoneHasBeenUsed = userMapper.findIdByTelephone(registerBody.getPhoneNumber()) != null;
		AppAssert.judge(phoneHasBeenUsed, new AppUserException("该电话已被注册"));
	}

	private String genMessageTemplate() {
		long minutes = effectiveTime / 1000;
		return messageTemplate + minutes + "秒: ";
	}

	private String handleAuthUserException(Exception exception) {
		exception.printStackTrace();
		String handleMessage = AppUserAuthExceptionHandle.getHandleMessage(exception.getClass());
		logger.info(handleMessage);
		return handleMessage;
	}
}
