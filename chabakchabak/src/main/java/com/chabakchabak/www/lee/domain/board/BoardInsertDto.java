package com.chabakchabak.www.lee.domain.board;


import java.util.List;


import lombok.Data;

@Data
public class BoardInsertDto {
	private Integer boardno;
	private String title;
	private String content;
	private String userid;
	private String nickname;
	private Integer boardtypeno;
	private List<AttachFileDto> pathList;
}
