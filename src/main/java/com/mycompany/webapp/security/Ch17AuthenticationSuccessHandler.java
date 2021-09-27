package com.mycompany.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//@Component
//public class Ch17AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
public class Ch17AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	private static final Logger logger = LoggerFactory.getLogger(Ch17AuthenticationSuccessHandler.class);
 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("실행");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}