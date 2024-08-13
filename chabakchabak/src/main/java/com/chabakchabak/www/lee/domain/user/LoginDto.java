package com.chabakchabak.www.lee.domain.user;

import lombok.Data;

@Data
public class LoginDto {
	private String userid;
	private String nickname;
	private String userlevel;
	private Integer point;
	private String profile;
}
