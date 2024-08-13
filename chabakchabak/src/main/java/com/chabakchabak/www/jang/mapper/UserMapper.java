package com.chabakchabak.www.jang.mapper;

import org.apache.ibatis.annotations.Param;

import com.chabakchabak.www.jang.domain.LoginSessionDto;
import com.chabakchabak.www.jang.domain.UserVo;


public interface UserMapper {
	//회원가입
	public int join(UserVo vo);
	
	// 로그인
	public LoginSessionDto login(@Param("userId") String userId, @Param("userPw") String userPw);
}
