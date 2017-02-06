package com.lanxi.weixin.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.weixin.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	private static Logger log = Logger.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test.do")
	public String test(Model model){
		log.info("测试。。。");
		int count = testService.test();
		model.addAttribute("count", count);
		return "test";
	}
}
