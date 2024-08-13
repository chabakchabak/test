package com.chabakchabak.www.kim.service;

import com.chabakchabak.www.kim.domain.LoginSessionDto;
import com.chabakchabak.www.kim.domain.UserVo;

public interface UserService {
	
	public boolean signUp(UserVo vo); 
	
	public UserVo login(LoginSessionDto dto) throws Exception;
}
