package com.chabakchabak.www.jang.domain;

import lombok.Data;

@Data
public class LoginSessionDto {
	private String userid;
	private String nickname;
	private Integer userlevel;
	private Integer point;
}
