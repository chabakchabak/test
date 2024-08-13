package com.chabakchabak.www.kim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chabakchabak.www.kim.domain.LoginSessionDto;
import com.chabakchabak.www.kim.domain.UserVo;
import com.chabakchabak.www.kim.service.UserService;



@Controller("kimUserController")
@RequestMapping("/Kim/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//회원가입 이동
	@GetMapping("/join")
	public String join() {
		return "";
	}
	
	//회원가입 완료
	@PostMapping("/joinrun")
	public String joinRun(UserVo vo) {
		boolean result = userService.signUp(vo);
		System.out.println("joinrun.. result : " + result);
		return "";
	}
	
	//로그인폼 이동
	@GetMapping("/login")
	public void login() {
	}
	
	//로그인 성공
	@PostMapping("/loginRun")
	public void loginPost(LoginSessionDto dto, Model model) throws Exception {
		UserVo vo = userService.login(dto);
		System.out.println("vo:" + vo);
		// 로그인 실패
		if (vo != null) {
			model.addAttribute("userVo", vo);
		}
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 현재 세션 무효화
		return "redirect:/";
	}
}
	
