package com.chabakchabak.www.lee.mapper.user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chabakchabak.www.lee.domain.user.JoinDto;
import com.chabakchabak.www.lee.domain.user.LevelDto;
import com.chabakchabak.www.lee.domain.user.LoginDto;
import com.chabakchabak.www.lee.domain.user.ProfileDto;


public interface UserMapper {
	//회원가입
	public int join(JoinDto dto);
	
	//가입 체크
	public int CheckUser(Map<String, String> map);
	
	//로그인 Dto 가져오기
	public LoginDto getLoginDto(String userid);
	
	//아이디 이메일로 가져오기
	public String getIdByEmail(String email);
	
	//이메일 가져오기
	public String getEmailById(String userid);
	
	//프로필 사진 가져오기
	public String getProfileImg(String userid);
	
	//프로필
	//프로필 업데이트
	public int updateProfile(Map<String, String> map);
	//프로필 정보 가져오기
	public ProfileDto getProfileDto(String userid);
	//닉네임 중복 조회
	public int checkNickname(Map<String, String> map);
	
	//닉네임 업데이트
	public int updateNickname(Map<String, String> map);
	//이메일 업데이트
	public int updateEmail(Map<String, String> map);
	//비밀번호 업데이트
	public int updatePw(Map<String, String> map);
	//포인트 업데이트
	public int updatePoint(@Param("userid") String userid, @Param("point") int point);
	//레벨 포인트 가져오기
	public LevelDto getLevelPoint(String userid);
	//레벨 업데이트	
	public int levelUpdate(@Param("userid") String userid, @Param("userlevel") int userlevel);
}
