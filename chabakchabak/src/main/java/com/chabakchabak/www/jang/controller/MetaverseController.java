package com.chabakchabak.www.jang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("jangMetaverseController")
@RequestMapping("/Jang/metaverse/*")
public class MetaverseController {
	
	@GetMapping("/main")
	public String main(HttpSession session) {
		System.out.println(session.getAttribute("loginSessionDto"));
		
		return "Jang/board/metaverse/main";
	}
	
}
