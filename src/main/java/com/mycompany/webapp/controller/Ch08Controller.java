package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({"inputForm"})
public class Ch08Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData",produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session,HttpServletRequest request) {
		logger.info("실행");
		logger.info("name: "+name);
		
		// 새션을 받는 두가지 방법이 있다.  
		
		session.setAttribute("name",name);
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping(value="/readData",produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session,@SessionAttribute("name") String name) {
		logger.info("실행");
		
		// 세션을 얻는 두가지 방법
		
		// 방법 1
		//String name =   (String)session.getAttribute("name");
		logger.info("name: "+name);
		
		
		// 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm(){
		logger.info("실행");
		return "ch08/loginForm";
	}
	
	
	@PostMapping("/login")
	public String login(String mid, String mpassword,HttpSession session) {
		logger.info("실행");
		
		if(mid.equals("spring")&&mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
			logger.info("로그인 성공");
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		
		logger.info("실행");
		// 방법1
		session.removeAttribute("sessionMid");
		// 방법2
		// 세션 객체 자체가 날라가기 때문에 세션에 저장된 모든 것들이 삭제된다.
		session.invalidate();
		
		return "redirect:/ch08/content";
	}
	
	@PostMapping(value="/loginAjax",produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword,HttpSession session) {
		logger.info("실행");
		
		
		String result = "";
		
		if(!mid.equals("spring")) {
			result = "wrongMid";
		}else if(!mpassword.equals("12345")) {
			result = "wrongMpassowrd";
		}else {
			result ="success";
			session.setAttribute("sessionMid", mid);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping(value="/logoutAjax",produces = "Application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(String mid, String mpassword,HttpSession session) {
		logger.info("실행");
		
		
		session.invalidate();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		
		return json;
	}
	
	// 세션에 inputForm이름이 존재하지 않을 때 딱 한번 실행된다.
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputForm() {
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
	
	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");

		return "ch08/inputStep1";
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {

		logger.info("실행");
		logger.info("data1: "+inputForm.getData1());
		logger.info("data2: "+inputForm.getData2());
		logger.info("data3: "+inputForm.getData3());
		logger.info("data4: "+inputForm.getData4());
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm,SessionStatus sessionStatus) {
		logger.info("실행");
		logger.info("data1: "+inputForm.getData1());
		logger.info("data2: "+inputForm.getData2());
		logger.info("data3: "+inputForm.getData3());
		logger.info("data4: "+inputForm.getData4());
		
		// 처리내용
		// 세션에 저장되어 있는 inputForm을 제거
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
}
