package com.mycompany.webapp.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;


@Controller
@RequestMapping("/ch17")
public class Ch17Controller {
private static final Logger logger = LoggerFactory.getLogger(Ch17Controller.class);
	

	
	@RequestMapping("/content")
	public String content() {
		return "ch17/content";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "ch17/loginForm";
	}
	
	@RequestMapping("/adminAction")
	public String adminAction() {
		logger.info("실행");
		return "redirect:/ch17/content";
	}
	
	@RequestMapping("/managerAction")
	public String managerAction() {
		logger.info("실행");
		return "redirect:/ch17/content";
	}
	
	@RequestMapping("/userAction")
	public String userAction() {
		logger.info("실행");
		return "redirect:/ch17/content";
	}
	
	
	@RequestMapping("/error403")
	public String error403() {
		logger.info("실행");
		return "error/error403";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		logger.info("실행");
		return "ch17/joinForm";
	}
	
	
	@Resource
	private Ch14MemberService memberService;
	
	@PostMapping("/join")
	public String join(Ch14Member member,Model model) {
		
		
		// 활성화 설정
		member.setMenabled(1);
		
		
		//패스워드 암호화
		String mpassword = member.getMpassword();
		//암호화 시키는 클래스
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//암호화된 패스워드를 저장할 때는 어떤 알고리즘을 사용했는지 추가해줘야 한다.
		mpassword = "{bcrypt}"+passwordEncoder.encode(mpassword);
		member.setMpassword(mpassword);
		
		JoinResult jr =  memberService.join(member);
		
		if(jr== JoinResult.SUCCESS) {
			return "redirect:/ch17/loginForm";
		}else if(jr == JoinResult.DUPLICATED) {
			model.addAttribute("error","중복된 아이디가 있습니다.");
			return "ch17/joinForm";
		}else {
			model.addAttribute("error","회원 가입이 실패되었습니다. 다시 시도해 주세요.");
			return "ch17/joinForm";
		}
		
	}
	
	@RequestMapping(value="/userInfo",produces="application/json; charset=utf-8")
	@ResponseBody
	public String userInfo(Authentication authentication) {
		logger.info("실행");
		
		//spring security가 인증 정보를 저장하는 컨테이너 객체를 얻기
		//SecurityContext securityContext = SecurityContextHolder.getContext();
		// 인증 정보 객체 얻는다.
		// 이 객체를 관리객체로 자동으로 만들어 준다.
		//Authentication authentication = securityContext.getAuthentication();
		// 사용자 식별값을 얻기
		String mid = authentication.getName();
		
		// 사용자 권한 이름 얻기
		List<String> authorityList = new ArrayList<>();
		// 권한이 여러개일 수 있다.
		for(GrantedAuthority authority: authentication.getAuthorities()) {
			authorityList.add(authority.getAuthority());
		}
		
		//사용자가 로그인한 PC의 IP주소 얻기
		
		WebAuthenticationDetails wad = (WebAuthenticationDetails)authentication.getDetails();
		String ip = wad.getRemoteAddress();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("mid",mid);
		jsonObject.put("mrole",authorityList);
		jsonObject.put("ip",ip);
		
		String json = jsonObject.toString();
		
		return json;
	}
}
