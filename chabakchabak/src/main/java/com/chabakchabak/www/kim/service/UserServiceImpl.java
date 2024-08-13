package com.chabakchabak.www.kim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chabakchabak.www.kim.domain.LoginSessionDto;
import com.chabakchabak.www.kim.domain.UserVo;
import com.chabakchabak.www.kim.mapper.UserMapper;


@Service("kimUserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean signUp(UserVo vo) {
//		int count = userMapper.join(vo);
//		if(count>0) {
//			return true;
//		}
//		return false;
		return false;
	}

	@Override
	public UserVo login(LoginSessionDto dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
