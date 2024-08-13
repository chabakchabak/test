package com.chabakchabak.www.jang.service;

import com.chabakchabak.www.jang.domain.LoginSessionDto;
import com.chabakchabak.www.jang.domain.UserVo;

public interface UserService {
	
	//회원가입
	public boolean signUp(UserVo vo); 
	
	// 로그인
	public LoginSessionDto login(String userId, String userPw);

}
