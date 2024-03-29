package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.webapp.controller.Ch09Controller;
import com.mycompany.webapp.dao.Ch13BoardDao1;



public class Ch13Service1 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service1.class);
	
	private Ch13BoardDao1 ch13BoardDao1;
	
	public Ch13Service1() {
		logger.info("Ch13BoardDao1() 실행");
	}
	
	// 외부에서 매개값을 받아서 생성자를 만듦
	// 주입을 위한 생성자 선언
	public Ch13Service1(Ch13BoardDao1 ch13BoardDao1) {
		logger.info("Ch13Service1(Ch13BoardDao1 ch13BoardDao1) 실행");
		this.ch13BoardDao1 = ch13BoardDao1;
	}
	
	// 주입을 위한 setter선언
	
	
	public void setCh13BoardDao1(Ch13BoardDao1 ch13BoardDao1) {
		logger.info("실행");
		this.ch13BoardDao1 = ch13BoardDao1;
	}
	
	public void method1() {
		logger.info("실행");
		ch13BoardDao1.update();
	}
}
