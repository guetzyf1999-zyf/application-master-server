package com.application.template.service.appUser;

import java.util.Date;

import com.application.template.aspectJ.annotation.TimeCount;
import com.application.template.entity.appUser.auth.JwtAuthResponseBody;
import com.application.template.service.authService.JwtService;
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

import com.application.template.config.applicationProps.MessageConfigProps;
import com.application.template.entity.appUser.AppUser;
import com.application.template.entity.appUser.auth.AuthBody;
import com.application.template.entity.appUser.auth.CaptchaAuthDTO;
import com.application.template.entity.appUser.auth.RegisterBody;
import com.application.template.enumtype.AppUserAuthExceptionHandle;
import com.application.template.enumtype.MessageSendingApproach;
import com.application.template.exceptionHandle.AppAssert;
import com.application.template.exceptionHandle.exception.AppUserException;
import com.application.template.mapper.appUser.AppUserMapper;
import com.application.template.service.factory.MessageSendingServiceFactory;
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
			UsernamePasswordAuthenticationToken authenticationTokentoken = new UsernamePasswordAuthenticationToken(authBody.getUsername(),
					authBody.getPassword());
			Authentication authenticate = SpringUtil.getBean(AuthenticationManager.class).authenticate(authenticationTokentoken);
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
	public CaptchaAuthDTO getCaptchaCode(RegisterBody registerBody) {
		MessageConfigProps messageConfigProps = SpringUtil.getBean(MessageConfigProps.class);
		MessageSendingApproach approach = MessageSendingApproach.valueOf(messageConfigProps.getCaptchasendingpath());
		MessageSendingServiceFactory serviceFactory = SpringUtil.getBean(approach.getMessageServiceFactoryClass());
		MessageService messageService = serviceFactory.getMessageService();
		String captcha = messageService.sendCaptchaMessage(registerBody, genMessageTemplate());
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
	public UserDetails loadUserByUsername(String usernameOrEmail) {
		AppUser user = userMapper.findUserByUsername(usernameOrEmail);
		return user != null ? user : userMapper.findUserByEmail(usernameOrEmail);
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
