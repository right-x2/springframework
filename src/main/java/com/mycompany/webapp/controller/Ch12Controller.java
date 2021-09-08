package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);
	
	 @RequestMapping("/content")
	 public String content() {
		 return "ch12/content";
	 }
	 
	 @GetMapping("/fileList")
	 public String fileList() throws IOException {
		 //파일 총 수 잋 파일 이름 목록 얻기 
		 
		 logger.info("실행");
		 
		 return "ch12FileListView";
		 
	 }
	 
	 
	 
	// 훨씬 더 좋은 방법이다.
		@GetMapping("/fileDownload")
		public String fileDownload(@ModelAttribute("fileName") String fileName, HttpServletRequest request,HttpServletResponse response,@ModelAttribute("userAgent") @RequestHeader("User-Agent")String userAgent) throws IOException {
			
			
			return "ch12FileDownloadView";
		}
}
