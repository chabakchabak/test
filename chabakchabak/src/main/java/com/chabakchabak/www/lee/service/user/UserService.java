package com.chabakchabak.www.lee.service.user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chabakchabak.www.lee.domain.user.JoinDto;
import com.chabakchabak.www.lee.domain.user.LevelDto;
import com.chabakchabak.www.lee.domain.user.LoginDto;
import com.chabakchabak.www.lee.domain.user.ProfileDto;


public interface UserService {
	
	//회원가입
	public boolean signUp(JoinDto dto); 
	
	//가입체크
	public boolean CheckUser(Map<String, String> map);
	
	//로그인 DTO 가져오기
	public LoginDto getLoginDto(String u_id);
	
	//아이디 가져오기
	public String getIdByEmail(String u_email);
	
	//비밀번호 업데이트
	public boolean updatePw(Map<String, String> map);
	
	//비밀번호 리셋
	public String resetPw(String u_id);
	
	//이메일 가져오기
	public String getEmailById(String u_id);
	
	//프로필
	//프로필 가져오기
	public ProfileDto getProfile(String u_id);
	//프로필 업데이트
	public boolean updateProfile(Map<String, String> map);
	//닉네임체크
	public boolean checkNick(Map<String, String> map) ;
	//닉네임 업데이트
	public boolean changeNickname(Map<String, String> map);
	//이메일 업데이트
	public boolean updateEmail(Map<String, String> map);
	//포인트
	public boolean updatePoint(@Param("userid") String userid, @Param("point") int point);
	//레벨정보가져오기
	public LevelDto getLevelInfo(String userid);
}
