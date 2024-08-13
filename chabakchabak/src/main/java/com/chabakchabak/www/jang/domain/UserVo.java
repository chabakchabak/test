package com.chabakchabak.www.jang.domain;

import lombok.Data;

@Data
public class UserVo {
	private String userId;
	private String userPw;
	private String nickname;
	private String email;
	private String regdate;
}
