package com.mycompany.webapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {
   private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
   
   @RequestMapping("/content")
   public String content() {
      logger.info("ch03 실행");
      return "ch03/content";
   }
   /*
   @GetMapping("/method1")
   public String method1(
		  String param1,
		  String param2,
		  String param3,
		  String param4,
		  String param5
	) {
      logger.info("method1 실행");
      logger.info(param1);
      logger.info(param2);
      logger.info(param3);
      logger.info(param4);
      logger.info(param5);
      return "redirect:/ch03/content";
   }
   */
   /*
   @GetMapping("/method1")
   public String method1(Ch03Dto dto) {
      logger.info("method1 실행");
      logger.info(" "+dto.getParam1());
      logger.info(" "+dto.getParam2());
      logger.info(" "+dto.getParam3());
      logger.info(" "+dto.isParam4());
      logger.info(" "+dto.getParam5());
      return "redirect:/ch03/content";
   }
   */
   
  
   
   @GetMapping("/method1")
   public String method1(
		  String param1,
		  @RequestParam(defaultValue = "0")int param2,
		  @RequestParam(defaultValue = "0.0")double param3,
		  @RequestParam(defaultValue = "false")boolean param4,
		  @RequestParam(defaultValue = "null")@DateTimeFormat(pattern="yyyy-mm-dd") Date param5
	) {
      logger.info("method1 실행");
      logger.info(" "+param1);
      logger.info(" "+param2);
      logger.info(" "+param3);
      logger.info(" "+param4);
      logger.info(" "+param5);
      return "redirect:/ch03/content";
   }
 
   /*
   @PostMapping("/method2")
   public String method2(
			  String param1,
			  String param2,
			  String param3,
			  String param4,
			  String param5
	) {
      logger.info("method2 실행");
      logger.info(param1);
      logger.info(param2);
      logger.info(param3);
      logger.info(param4);
      logger.info(param5);
      return "redirect:/ch03/content";
   }
   */
   /*
   @PostMapping("/method2")
   public String method2(
		   String param1,
		   @RequestParam(defaultValue = "0")int param2,
		   @RequestParam(defaultValue = "0.0")double param3,
		   @RequestParam(defaultValue = "false")boolean param4,
		   @RequestParam(defaultValue = "null")@DateTimeFormat(pattern="yyyy-mm-dd") Date param5
	) {
	   logger.info("method1 실행");
	   logger.info(" "+param1);
	   logger.info(" "+param2);
	   logger.info(" "+param3);
	   logger.info(" "+param4);
	   logger.info(" "+param5);
	   return "redirect:/ch03/content";
   }
   */
   
   @PostMapping("/method2")
   public String method2(Ch03Dto dto) {
	   logger.info("method2 실행");
	   logger.info(" "+dto.getParam1());
	   logger.info(" "+dto.getParam2());
	   logger.info(" "+dto.getParam3());
	   logger.info(" "+dto.isParam4());
	   logger.info(" "+dto.getParam5());
	   return "redirect:/ch03/content";
   }
}