package com.chabakchabak.www.kim.domain;

import lombok.Data;

@Data
public class LoginSessionDto {
	private String userId;
	private String nickName;
	private Integer userLevel;
	private Integer point;
}
