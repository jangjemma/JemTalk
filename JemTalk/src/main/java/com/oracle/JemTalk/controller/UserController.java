package com.oracle.JemTalk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.JemTalk.configuration.auth.PrincipalDetails;
import com.oracle.JemTalk.dto.ChatUser;
import com.oracle.JemTalk.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/")
	public String startMainPage() {
		System.out.println("startMainPage....");
		return "redirect:/loginSuccess";
	}
	
	@RequestMapping("/loginForm")
	public String LoginForm() {
		log.info("loginFormStart...");
		return "/user/loginForm";
	}
	
	@RequestMapping("/signUp")
	public String SignUp() {
		log.info("signUpFormStart...");
		return "/user/signUpForm";
	}
	
	@RequestMapping("/signUpAction")
	public String signUpAction(ChatUser chatUser,String v_pwd, String v_mail_id, String v_mail_domain, Model model) {
		log.info("signUpActionStart...");
		chatUser.setPassword(encoder.encode(v_pwd));
		chatUser.setUser_mail(v_mail_id + "@" + v_mail_domain);
		int result = userService.signUpAction(chatUser);
		model.addAttribute("msg", result);
		return "/user/loginForm";
	}
	
	
	@RequestMapping("/findIdPwd")
	public String FindIdPwd() {
		log.info("findIdPwdFormStart...");
		return "/user/findIdPwdForm";
	}
	
	@RequestMapping("/loginSuccess")
	public String LoginResult(@AuthenticationPrincipal PrincipalDetails principal, HttpSession session) {
		log.info("user info" + principal.getChatUser());
		session.setAttribute("chatUser", principal.getChatUser());
		log.info("loginSuccess Start...");
		return "redirect:/user/mainPage";
	}
	
	@RequestMapping("/loginFail")
	public String loginFail() {
		log.info("loginFail Start...");
		return "loginForm";
	}
}
