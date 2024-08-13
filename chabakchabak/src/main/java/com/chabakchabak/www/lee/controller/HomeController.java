package com.chabakchabak.www.lee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

	//index
	@GetMapping("/")
	public String home() {
		return "lee/index/index";
	}

}
