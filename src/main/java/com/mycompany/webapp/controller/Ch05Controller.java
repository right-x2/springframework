package com.mycompany.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	

	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request) {
		logger.info("실행");
		
		logger.info("method "+request.getMethod());
		logger.info("requestURI "+request.getRequestURI());
		logger.info("client IP주소 "+request.getRemoteAddr());
		logger.info("ContextRoot "+request.getContextPath());
		
		String userAgent = request.getHeader("User-Agent");
		logger.info("userAgent "+userAgent);
		
		if(userAgent.contains("Windows NT")) {
			logger.info("client OS: Window");
		}else if(userAgent.contains("Macintosh")) {
			logger.info("client OS: MacOS");
		}
		
		
		if(userAgent.contains("Edg")) {
			logger.info("client Browser: Edge");
		}else if(userAgent.contains("Cecko")) {
			logger.info("client Browser: Trident");
		}else if(userAgent.contains("Chrome")) {
			logger.info("client Browser: Chrome");
		}else if(userAgent.contains("Safari")) {
			logger.info("client Browser: Safari");
		}
		
		
		return "redirect:/ch05/content";

	}
	
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		logger.info("쿠키 생성 실행");
		
		Cookie cookie = new Cookie("useremail", "kjw970103@gmail.com");
		
		cookie.setPath("/"); //localhost면 전
		cookie.setMaxAge(30*60);// 이 시간동안에만 전
		cookie.setHttpOnly(true); // js에서 접근이 불가능하게 
		cookie.setSecure(true); // https에서만 전
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	
	@GetMapping("/getCookie1")
	public String getCookie1(@CookieValue String useremail,@CookieValue("useremail") String uemail) {
		logger.info("쿠키 저장1 실행");
		
		
		logger.info("useremail "+uemail);
		
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getCookie2")
	public String getCookie2(HttpServletRequest request) {
		logger.info("쿠키 저장2 실행");
		
		Cookie[] cookies = request.getCookies();
		
		
		for(Cookie cookie: cookies) {
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			
			if(cookieName.equals("useremail")) {
				logger.info(cookieValue);
				break;
			}
		}
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("실행");
		
		String json = "{\"userid\":\"fail\",\"useremail\":\"fall@company.com\",\"username\":\"홍길동\"}";
		/*
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userid", "fall");
		jsonObject.put("useremail", "fall@company.com");
		jsonObject.put("usertel", "0101231234");
		String json = jsonObject.toString(); 
		*/
		
		// 만약 값에 한글이 들어가면 반드시 UTF-8로 인코딩해줘야 
		logger.info("json "+json);
		json = URLEncoder.encode(json, "UTF-8");
		logger.info("json "+json);
		
		
		Cookie cookie = new Cookie("userinfo", json);
		
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/getJsonCookie")
	public String getJsonCookie(@CookieValue String userinfo) {
		logger.info("실행");
		logger.info("user "+userinfo);
		JSONObject jsonObject = new JSONObject(userinfo);
		logger.info("userid "+jsonObject.getString("userid"));
		logger.info("useremail "+jsonObject.getString("useremail"));
		logger.info("username "+jsonObject.getString("username"));
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/createJwtCookie")
	public String createJwtCookie(HttpServletResponse response) throws UnsupportedEncodingException{
		logger.info("실행");
		
		String userid = "fall";
		String useremail = "fall@company.com";
		String username = "홍길동";
		
		JwtBuilder builder = Jwts.builder();
		
		// header
		builder.setHeaderParam("alg", "HS256");
		builder.setHeaderParam("typ", "JWT");
		builder.setExpiration(new Date(new Date().getTime()+ 1000*60*30));
		
		
		// payload
		builder.claim("userid", userid);
		builder.claim("useremail", useremail);
		builder.claim("username", username);
		
		
		//signature
		
		String secretKey = "abc12345";
		builder.signWith(SignatureAlgorithm.HS256,secretKey.getBytes("UTF-8"));
		
		String jwt = builder.compact();
		
		logger.info("jwt: "+jwt);
		
		
		Cookie cookie = new Cookie("jwt", jwt);
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	
	
	@GetMapping("/getJwtCookie")
	public String getJwtCookie(@CookieValue String jwt) throws UnsupportedEncodingException{
		logger.info("실행");
		JwtParser parser = Jwts.parser();
		String secretKey = "abc12345";
		parser.setSigningKey(secretKey.getBytes("UTF-8"));
		Jws<Claims> jws = parser.parseClaimsJws(jwt);
		Claims claims = jws.getBody();
		String userid = claims.get("userid",String.class);
		String useremail = claims.get("useremail",String.class);
		String username = claims.get("username",String.class);
		
		logger.info(userid);
		logger.info(useremail);
		logger.info(username);
		return "redirect:/ch05/content";
	}
}
