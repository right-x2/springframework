package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Member;
import com.mycompany.webapp.validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.validator.Ch04MemberIdValidator;
import com.mycompany.webapp.validator.Ch04MemberJoinFormValidator;
import com.mycompany.webapp.validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.validator.Ch04MemberTelValidator;
import com.sun.org.slf4j.internal.Logger;

@Controller
@RequestMapping("/ch04")
public class Ch04Controller {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Ch04Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}
	
	@PostMapping("/method1")
	public String method1() {
		return "redirect:/ch04/content";
	}
	
	@InitBinder("joinForm")
	public void joinFormSetValidate(WebDataBinder binder) {
		logger.info("실행");
		
		// 폼단위 validator
		//binder.setValidator(new Ch04MemberJoinFormValidator());
		
		// 필드단위 validator
		binder.addValidators(
			new Ch04MemberIdValidator(),
			new Ch04MemberPasswordValidator(),
			new Ch04MemberEmailValidator(),
			new Ch04MemberTelValidator()
		);
	}
	@PostMapping("/join")
	public String join(@ModelAttribute("joinForm") @Valid Ch04Member member, Errors errors) {
		logger.info("실행");
		if(errors.hasErrors()) {
			logger.info("다시 입력폼 제공 + 에러 메시지");
			
			//forward
			return "ch04/content";
		}else {
			logger.info("정상 요청 처리 후 응답 제공");
			return "redirect:/";
		}
	}
	
	
	@InitBinder("loginForm")
	public void loginFormSetValidate(WebDataBinder binder) {
		logger.info("실행");
		
		// 폼단위 validator
		//binder.setValidator(new Ch04MemberJoinFormValidator());
		
		// 필드단위 validator
		binder.addValidators(
			new Ch04MemberIdValidator(),
			new Ch04MemberPasswordValidator()
		);
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid Ch04Member member, Errors errors) {
		logger.info("실행");
		if(errors.hasErrors()) {
			logger.info("다시 입력폼 제공 + 에러 메시지");
			
			// foward
			
			return "ch04/content";
		}else {
			
			//redirect
			logger.info("정상 요청 처리 후 응답 제공");
			return "redirect:/";
		}
	}
}
