package com.mycompany.webapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.webapp.controller.Ch10Controller;

//객체로 생성해서 관리하도록 설정을 할 때
@Component
//모든 컨트롤러에 영향을 미치는 설정을 할 때
@ControllerAdvice
public class Ch10ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(Ch10ExceptionHandler.class);
	
	public Ch10ExceptionHandler() {
		
		logger.info("생성자 실행");
	}
	
	//예외 처리자 설정
	@ExceptionHandler
	public String handleNullPointerException(NullPointerException e) {
		e.printStackTrace();
		logger.info("실행");
		return "error/500_null";
	}	
	
	
	@ExceptionHandler
	public String handleClassCastException(ClassCastException e) {
		e.printStackTrace();
		logger.info("실행	");
		return "error/500_cast";
	}	
	
	
	@ExceptionHandler
	public String handleOtherException(Exception e) {
		e.printStackTrace();
		logger.info("실행");
		return "error/500";
	}	
	
	
	@ExceptionHandler
	public String handleCh16NotFoundAccountException(Ch16NotFoundAccountException e,Model model) {
		logger.info("실행");
		e.printStackTrace();
		model.addAttribute("error", e.getMessage());
		return "error/notFoundAccountException";
	}
	
	
	@ExceptionHandler
	public String handleCh16NotEnoughBalanceException(Ch16NotEnoughBalanceException e,Model model) {
		logger.info("실행");
		e.printStackTrace();
		model.addAttribute("error", e.getMessage());
		return "error/notEnoughBalanceException";
	}
	
	
	@ExceptionHandler
	public String handleCh10SoldOutException(Ch10SoldOutException e) {
		e.printStackTrace();
		logger.info("실행");
		return "error/soldout";
	}	
}
