package com.chabakchabak.www.lee.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chabakchabak.www.lee.mapper.board.BoardTypeMapper;


@Service("leeBoardTypeService")
public class BoardTypeServiceImpl implements BoardTypeService{
	@Autowired
	private BoardTypeMapper boardtypemapper;
	@Override
	public String getBoardType(int boardtypeno) {
		return boardtypemapper.getBoardType(boardtypeno);
	}
	@Override
	public int getBoardTypeNo(String boardtype) {
		return boardtypemapper.getBoardTypeNo(boardtype);
	}
	
}
