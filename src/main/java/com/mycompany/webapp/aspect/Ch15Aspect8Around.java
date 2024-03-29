package com.mycompany.webapp.aspect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class Ch15Aspect8Around {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect8Around.class);
	
	
	@Around("execution(public * com.mycompany.webapp.controller.Ch15Controller.boardList1(..))")
	public Object loginCheckAdvice1(ProceedingJoinPoint joinPoint) throws Throwable {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();		
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sessionMid");
		logger.info("실행");
		if(mid==null) {
			return "Ch15/authFail";
		}else {
			//request.setAttribute("loginCheck", true);
			Object result = joinPoint.proceed();
			return result;
		}
		

		
	}
	@Around("execution(public * com.mycompany.webapp.controller.Ch15Controller.boardList2(..))")
	public Object loginCheckAdvice2(ProceedingJoinPoint joinPoint) throws Throwable {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();		
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sessionMid");
		logger.info("실행");
		if(mid==null) {
			logger.info("로그인 안됨");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result","authFail");
			String json = jsonObject.toString();
			HttpServletResponse response = sra.getResponse();
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
			pw.flush();
			pw.close();
			return null;
		}else {
			
			Object result = null;
			result = joinPoint.proceed();
			return result;
		}
	} 
}
