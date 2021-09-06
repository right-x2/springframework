package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileUpload(String title, String desc, MultipartFile attach) throws IllegalStateException, IOException {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: "+title);
		logger.info("desc: "+desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: "+ attach.getOriginalFilename());
		logger.info("file contenttype: "+ attach.getContentType());
		logger.info("file size: "+ attach.getSize());
		
		String savedname = new Date().getTime()+"-"+attach.getOriginalFilename();
		
		// 파일파트 데이터를 서버의 파일로 저장
		File file = new File("/Users/kimjungwoo/Documents/hyundai_it/upload_files/"+savedname);
		attach.transferTo(file);
		return "redirect:/ch09/content";
		
	}
	
	
	
	@PostMapping(value = "/fileuploadAjax",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAjax(String title, String desc, MultipartFile attach) throws IllegalStateException, IOException {
		logger.info("실행");
		
		// 문자 파트 내용 읽기
		logger.info("title: "+title);
		logger.info("desc: "+desc);
		
		// 파일 파트 내용 읽기
		logger.info("file originalname: "+ attach.getOriginalFilename());
		logger.info("file contenttype: "+ attach.getContentType());
		logger.info("file size: "+ attach.getSize());
		
		String savedname = new Date().getTime()+"-"+attach.getOriginalFilename();
		
		// 파일파트 데이터를 서버의 파일로 저장
		File file = new File("/Users/kimjungwoo/Documents/hyundai_it/upload_files/"+savedname);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname",savedname);
		String json = jsonObject.toString();
		
		return json;
	}
	// 응답 바디의 데이터 형식 고정
	// toByteArray()에 리턴하는 배열의 길이가 문제
	/*
	@GetMapping(value= "/filedownload",produces = "image/png")
	@ResponseBody
	public byte[] filedownload(String savedname) throws IOException {
		
		String filePath = "/Users/kimjungwoo/Documents/hyundai_it/upload_files/"+savedname;
		
		InputStream is = new FileInputStream(filePath);
		byte[] data = IOUtils.toByteArray(is); // 파일의 크기가 크면 좋은 방법이 아니다.
		// 메모리가 크면 문제가 생긴다.
		// 파일의 크기만큼 배열이 만들어지기 때문에 
		return data;
	}
	*/
	
	// 훨씬 더 좋은 방법이다.
	@GetMapping("/filedownload")
	public void filedownload(int fileNo, HttpServletResponse response,@RequestHeader("User-Agent")String userAgent) throws IOException {
		
		// fileno로 디비에서 퍼알 정보 거죠오 
		String contentType = "image/jpeg";
		String originalFilename = "눈내리는마을.jpg";
		String savedName = "1630656717476-눈내리는마을.jpg";
		
		// http 헤더에는 아스키 문자만 들어가지만 한글은 포함안됨 그래서 바꾸어 주어랴 한다.
		
		// 크롬 브라우저에서 한글 파일명을 변환
		
		
		// 응답 버다의 데이ㅣ터의 형식
		System.out.println(userAgent);
		response.setContentType(contentType);
		
		if(userAgent.contains("Tredent")||userAgent.contains("MSIE")) {
			//IE일 경우
			originalFilename = URLEncoder.encode(originalFilename,"UTF-8");
		}else {
			// 크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"),"ISO-8859-1");
		}
		//응답헤더에 이 헤더가 포함이 되면 지시한대로 다운로드가 되어야 한다.
		response.setHeader("Content-Disposition", "attachment; filename=\""+originalFilename+"\"");
		// 파일을 첨부로 다운로드하도록 설정 응답헤더에 지정하는 것이다.
		
		
		// 파일로부터 데이터를 읽는 입력스트림 생성
		String filePath = "/Users/kimjungwoo/Documents/hyundai_it/upload_files/"+savedName;
		InputStream is = new FileInputStream(filePath);
		
		
		
		// 응답 바디에 출력하는 출력스트임 얻기
		OutputStream os = response.getOutputStream();
		
		// 입력스트림 -> 출력스트림
		
		FileCopyUtils.copy(is,os); // 파일사이즈가 커져도 무리가 없다. -> 1메가씩 읽고 출력하기 때문에 메모리를 많이 차지하지 않는다.
		is.close();
		os.flush();
		os.close();
		
	}
	
}
