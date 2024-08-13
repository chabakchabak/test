package com.chabakchabak.www.lee.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chabakchabak.www.lee.domain.board.BoardTypeVo;
import com.chabakchabak.www.lee.domain.user.JoinDto;
import com.chabakchabak.www.lee.domain.user.LevelDto;
import com.chabakchabak.www.lee.domain.user.LoginDto;
import com.chabakchabak.www.lee.domain.user.ProfileDto;
import com.chabakchabak.www.lee.service.user.UserService;
import com.chabakchabak.www.lee.util.MyFileUtil;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller("leeUserController")
@RequestMapping("/lee/user/*")
@Qualifier("leeUserService")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//**로그인 페이지 이동**
	@GetMapping("/login")
	public void login(HttpServletRequest req, HttpSession session) {
		System.out.println("로그인페이지");
		String curLocation = req.getHeader("referer");
		System.out.println("현재페이지 : " + curLocation);
		session.setAttribute("curLocation", curLocation);
	}
		
	// 로그인 시 가입유무 체크
	@ResponseBody
	@PostMapping("/checkUser")
	public boolean checkUser(@RequestParam("userid") String userid, 
							 @RequestParam("userpw") String userpw ) {
		log.info("checkUser..................................");
//		log.info("u_id : " + u_id);
//		log.info("u_id : " + u_id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("userpw", userpw);
//		log.info("map : " + map);
		boolean result = userService.CheckUser(map);
		return result;
	}
		
	// 로그인 처리
	@PostMapping("/loginRun")
	public ModelAndView loginRun(@RequestParam("userid") String userid, 
						 @RequestParam(value="checked", required=false) boolean checked,
						 HttpServletRequest request,HttpServletResponse response,
						 Model model, HttpSession session) throws Exception{
		log.info("loginRun.................................");
		log.info("checked : " + checked);
		
		//쿠키처리
		MyFileUtil.CheckIdCookie(userid, checked, request, response);
		
		ModelAndView mav = new ModelAndView();
		LoginDto login = userService.getLoginDto(userid);
		log.info("login : " + login);
		if(login != null) {
			mav.addObject("login", login);
		}
		return mav;
	}
		
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest req) {
		BoardTypeVo vo = (BoardTypeVo)session.getAttribute("boardtype");
		session.invalidate();
		HttpSession newSession = req.getSession(true);
		newSession.setAttribute("boardtype", vo);
		String uri = req.getHeader("Referer");
		
		return "redirect:"+uri;
	}
	
	//**프로필 정보 변경
	
	//프로필변경
	//프로필 페이지
	
	@GetMapping("/profile")
	public void profile(HttpSession session) {	
		log.info("profile...............................");
		LoginDto dto = (LoginDto)session.getAttribute("loginSessionDto");
		String userid = dto.getUserid();
		log.info("userid : " + userid);
		ProfileDto profileDto = userService.getProfile(userid);
		session.setAttribute("profile", profileDto);
	}
	
	//프로필 업데이트
	@PostMapping("/profileUpdate")
	public String profileUpdate(@RequestParam("userid") String userid, 
							     @RequestParam("profile") String profile) {
		log.info("profileUpdate..........................");
		log.info("userid : " + userid);
		log.info("profile : " + profile);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("profile", profile);
				
		userService.updateProfile(map);
		return "redirect:/lee/user/profile";
	}
	
	//닉네임 중복 체크
	@ResponseBody
	@PostMapping("/checkNickname")
	public boolean checkNickname(@RequestParam("userid") String userid, 
								 @RequestParam("nickname") String nickname) {
		log.info("checkNickname....................................");
		log.info("userid : " + userid);
		log.info("nickname : " + nickname);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("nickname", nickname);
		boolean result = userService.checkNick(map);
		return result;
	}
	
	//닉네임 변경
	@PostMapping("/changeNickname")
	public String changeNickname(@RequestParam("userid") String userid, 
							     @RequestParam("nickname") String nickname, RedirectAttributes rttr) {
		log.info("changeNickname");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("nickname", nickname);
		boolean result = userService.changeNickname(map);
		String resultStr = "";
		if(result == true) {
			resultStr = "success";
		}else {
			resultStr = "fail";
		}
		rttr.addAttribute("resultUpdateNick", resultStr);
		log.info("resultStr : " + resultStr);
		
		return "redirect:/lee/user/profile";
	}

//	이메일 본인인증
	@ResponseBody
	@PostMapping("/certiEmail")
	public String certiEmail(String email) {
		log.info("checkEmail......................................");
		String uuid = UUID.randomUUID().toString();
		String certi = uuid.substring(0, uuid.indexOf("-"));
		log.info("uuid : " + uuid);
		log.info("certi : " + certi);
		log.info("email : " + email);
		
		MimeMessagePreparator prepare = new MimeMessagePreparator() {
				
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
				helper.setFrom("asraisin@gmail.com");
				helper.setTo(email);
				helper.setSubject("인증번호 안내");
				helper.setText("인증번호 : ", certi);
			}
		};
				
		mailSender.send(prepare);
		log.info("certi : " + certi);
		
		return certi;
	}
	
	//이메일 업데이트
	@PostMapping("/emailUpdate")
	public String emailUpdate(@RequestParam("userid") String userid, @RequestParam("email") String email, RedirectAttributes rttr) {
		log.info("emailUpdate...........................");
		log.info("user info : " + userid + ", " + email);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("email", email);
		boolean result = userService.updateEmail(map);
		log.info("result : " + result);
		String resultStr = "";
		if(result == true) {
			resultStr = "success";
		}else {
			resultStr = "fail";
		}
		rttr.addAttribute("resultUpdateEmail", resultStr);
		return "redirect:/lee/user/profile";
	}
		
	//비밀번호 변경
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam("userid") String userid, 
								 @RequestParam("userpw") String userpw, 
								 RedirectAttributes rttr) {
		log.info("updatePassword...........................");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("userpw", userpw);
		boolean result = userService.updatePw(map);
		log.info("result : " + result);
		String resultStr = "";
		if(result == true) {
			resultStr = "success";
		}else {
			resultStr = "fail";
		}
		rttr.addAttribute("resultUpdatePw", resultStr);
		return "redirect:/lee/user/profile";
	}
	
	//*** 회원 가입및 아이디 비밀번호 찾기
	//join
	//회원가입페이지
	@GetMapping("/join")
	public void join() {}
	
	//회원가입처리
	@PostMapping("/joinRun")
	public String joinRun(JoinDto dto) {
		boolean result = userService.signUp(dto);
		System.out.println("joinrun.. result : " + result);
		return "redirect:/lee/user/login";
	}
	
	//**아이디찾기
	//페이지이동
	@GetMapping("/forgot-id")
	public void forgotId() {}
	
	//아이디 이메일로 전송
	@PostMapping("/findId")
	public String findId(String email, RedirectAttributes rttr) {
		log.info("findId.............................");
		String userId = userService.getIdByEmail(email);
		
		String resultMessage = "fail";
		if(userId!=null) {
			MimeMessagePreparator prepare = new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
					helper.setFrom("asraisin@gmail.com");
					helper.setTo(email);
					helper.setSubject("아이디 안내");
					helper.setText("아이디 : ", userId);
				}
			};
			
			mailSender.send(prepare);
			resultMessage = "success";
		}
		
		rttr.addAttribute("resultFindId", resultMessage);
		
		return "redirect:/lee/user/login";
	}
	
	//**비밀번호찾기
	//페이지이동
	@GetMapping("/forgot-password")
	public void forgotPassword() {}
	
	//비밀번호 초기화
	@PostMapping("/resetPassword")
	public String resetPassword(String userid, RedirectAttributes rttr) {
		log.info("resetPassword.........................");
		String userEmail = userService.getEmailById(userid);
		String userpw = userService.resetPw(userid);
		
		String resultMessage = "fail";
		if(userpw!=null) {
			MimeMessagePreparator prepare = new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
					helper.setFrom("asraisin@gmail.com");
					helper.setTo(userEmail);
					helper.setSubject("비밀번호 변경안내");
					helper.setText("변경된 비밀번호 : ", userpw);
				}
			};
			
			mailSender.send(prepare);
			resultMessage = "success";
		}
		
		rttr.addAttribute("resultResetPw", resultMessage);
		return "redirect:/lee/user/login";
	}
	
	//레벨정보 가져오기
	@ResponseBody
	@GetMapping("/getLevelInfo/{userid}")
	public LevelDto getLevelInfo(@PathVariable("userid") String userid){
		log.info("userController, getLevelInfo......................");
		log.info("userid : " + userid);
		return userService.getLevelInfo(userid);
	}
}
