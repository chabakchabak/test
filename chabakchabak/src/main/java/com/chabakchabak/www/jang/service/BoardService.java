package com.chabakchabak.www.jang.service;

import java.util.List;

import com.chabakchabak.www.jang.domain.BoardVo;
import com.chabakchabak.www.jang.domain.NoticeCriteria;
import com.chabakchabak.www.jang.domain.NoticeDto;


public interface BoardService {
	public List<NoticeDto> getNoticeList();
	public NoticeDto getNotice(Long bno);
	public int getTotal(NoticeCriteria cri);
	public List<NoticeDto> getListWithPaging(NoticeCriteria cri);
	public void viewsUp(Long bno);
	public void insertNotice(BoardVo vo);
	public boolean modify(String content, Long bno);
	public boolean remove(Long bno);
}
