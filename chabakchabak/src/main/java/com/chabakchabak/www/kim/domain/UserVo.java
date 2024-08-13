package com.chabakchabak.www.kim.domain;


import java.util.Date;

import lombok.Data;

@Data
public class UserVo {
	private String userid;
	private String userpw;
	private String nickname;
	private String email;
	private Integer lv;
	private Integer point;
	private Date regdate;
}
