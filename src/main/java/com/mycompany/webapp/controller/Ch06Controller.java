package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);

	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@RequestMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@RequestMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		logger.info("실행");
		return "ch06/fragmentHtml";
	}
	
	
	
	// 리턴이 없더라고 브라우저로 http가 간다.
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws IOException {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo5.jpg");
		
		String json = jsonObject.toString();
		
		
		// 여기서 직접 응답을 만들어 내기 때문에 JSP를 리턴할 필요가 없다.
		// 응답 http의 body부분에 json을 포함한다.
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
	}
	
	
	// 스프링같은 코드 
	// 리턴되는 것을 body에 들어가게 한다.
	// content type을 producer로 지정할 수 있다.
	@GetMapping(value = "/getJson2",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getJson2() {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		
		String json = jsonObject.toString();
		
		return json;
	}
	
	
	@GetMapping("/getJson3")
	public String getJson3() {
		logger.info("실행");

		
		return "redirect:/";
	}
}
