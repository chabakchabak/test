package com.chabakchabak.www.jang.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chabakchabak.www.jang.domain.LoginSessionDto;
import com.chabakchabak.www.jang.domain.UserVo;
import com.chabakchabak.www.jang.service.UserService;



@Controller("jangUserController")
@RequestMapping("/Jang/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	//join
	//회원가입페이지
	@GetMapping("/join")
	public String join() {
		return "index/join";
	}
	
	//회원가입처리
	@PostMapping("/joinrun")
	public String joinRun(UserVo vo) {
		boolean result = userService.signUp(vo);
		System.out.println("joinrun.. result : " + result);
		return "index/index";
	}
	
	// 로그인폼
	@GetMapping("/login")
	public String loginForm(@RequestParam("currentUrl") String currentUrl, HttpServletRequest request) {
		request.setAttribute("currentUrl", currentUrl);
		System.out.println(currentUrl);
		return "Jang/index/login";
	}
	
	
	// 로그인
	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,
						@RequestParam("currentUrl") String currentUrl, HttpSession session) {
		System.out.println("userId: " + userId);
		System.out.println("userPw: " + userPw);
		LoginSessionDto loginSesionDto = userService.login(userId, userPw);
		System.out.println("loginVo :" + loginSesionDto);
		if(loginSesionDto != null) {
			System.out.println("로그인 성공");
			session.setAttribute("loginSessionDto", loginSesionDto);
			return "redirect:/" + currentUrl;
		}
		System.out.println("로그인 실패");
		 return "redirect:/Jang/user/login?currentUrl=" + currentUrl; 
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(@RequestParam("currentUrl") String currentUrl, HttpSession session) {
		session.invalidate();
		return "redirect:/" + currentUrl;
	}
}
