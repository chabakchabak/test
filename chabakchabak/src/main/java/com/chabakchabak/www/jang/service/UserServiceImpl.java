package com.chabakchabak.www.jang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chabakchabak.www.jang.domain.LoginSessionDto;
import com.chabakchabak.www.jang.domain.UserVo;
import com.chabakchabak.www.jang.mapper.UserMapper;


@Service("JangUserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean signUp(UserVo vo) {
		int count = userMapper.join(vo);
		if(count>0) {
			return true;
		}
		return false;
	}

	@Override
	public LoginSessionDto login(String userId, String userPw) {
		LoginSessionDto loginSessionDto = userMapper.login(userId, userPw);
		return loginSessionDto;
	}

}
