package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.loginResult;

@Controller
@RequestMapping("/ch15")
public class Ch15Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch15Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch15/content";
	}
	
	@RequestMapping("/before")
	public String before() {
		logger.info("실행");
		return "redirect:/ch15/content"; 
	}
	
	@RequestMapping("/after")
	public String afterXXX() {
		logger.info("실행");
		return "redirect:/ch15/content"; 
	}
	
	
	@RequestMapping("/afterReturning")
	public String afterReturning() {
		logger.info("실행");
		return "redirect:/ch15/content"; 
	}
	
	
	@RequestMapping("/afterThrowing")
	public String afterThrowing() {
		logger.info("실행");
		if(true)
			 throw new RuntimeException("테스트는 예외입니다.");
		return "redirect:/ch15/content"; 
	}
	
	@RequestMapping("/around")
	public String around() {
		logger.info("실행");
		return "redirect:/ch15/content"; 
	}
	
	@Resource
	private Ch14BoardService boardService;
	
	@RequestMapping("/runtimeCheck")
	public String runtimeCheck() {
		logger.info("실행");
		Pager pager = new Pager(10,5,boardService.getTotalBoardNum(),1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		return "redirect:/ch15/content"; 
	}
	
	
	@RequestMapping("/authCheck")
	public String authCheck() {
		logger.info("실행");
		return "redirect:/ch15/content"; 
	}
	
	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");
		return "ch15/loginForm";
	}
	
	
	@Resource
	private Ch14MemberService memberService;
	
	@PostMapping("/login")
	public String login(Ch14Member member,Model model,HttpSession session) {
		logger.info("실행");
		
		loginResult result = memberService.login(member);
		
		if(result== loginResult.SUCCESS) {
			session.setAttribute("sessionMid", member.getMid());
			return "redirect:/";
		}else if(result == loginResult.WRONG_ID) {
			model.addAttribute("error","아이디가 존재하지 않습니다.");
			return "ch15/loginForm";
		}else if(result == loginResult.WRONG_PASSWORD){
			model.addAttribute("error","아이디와 비밀번호가 일치하지 않습니다."); 
			return "ch14/loginForm";
		}else {
			model.addAttribute("error","로그인에 실패했습니다."); 
			return "ch14/loginForm";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");
		session.removeAttribute("sessionMid");
		return "redirect:/";
		
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		logger.info("실행");
		Pager pager = new Pager(5,5,boardService.getTotalBoardNum(),1);
		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards",boards);
		return "ch15/boardList";
	}
}
