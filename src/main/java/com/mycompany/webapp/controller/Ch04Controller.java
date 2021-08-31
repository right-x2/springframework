package com.mycompany.webapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Member;
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

	@PostMapping("/method2")
	public String method2(Ch04Member member) {
		logger.info("mid: "+member.getMid());
		logger.info("mpassword: "+member.getMpassword());
		logger.info("memail: "+member.getMemail());
		logger.info("mtel: "+member.getMtel());
		
		return "redirect:/ch04/content";
	}

}
