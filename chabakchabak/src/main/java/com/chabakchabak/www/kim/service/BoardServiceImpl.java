package com.chabakchabak.www.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chabakchabak.www.kim.domain.AttachBoardDto;
import com.chabakchabak.www.kim.domain.InformationBoardVo;
import com.chabakchabak.www.kim.domain.InformationCriteria;
import com.chabakchabak.www.kim.mapper.AttachMapper;
import com.chabakchabak.www.kim.mapper.BoardMapper;


@Service("kimBoardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	

	// getlistksy
	@Override
    public List<InformationBoardVo> getListKsy(InformationCriteria criteria) {
		System.out.println("getInfoList...");
		List<InformationBoardVo> list = boardMapper.getListWithPagingKsy(criteria);
		
		
        return list;
	}
	
	// gettotalksy
    @Override
    public int getTotalKsy(InformationCriteria criteria) {
    	int count = boardMapper.getTotalCountKsy(criteria);
        return count;
    }
    
    // 정보게시판 주간 베스트 게시물 가져오기 김세영
    @Override
    public List<InformationBoardVo> getWeeklyBest() {
        return boardMapper.getWeeklyBest();
    }
    
    // 오늘의 통합 best 게시물 가져오기 김세영
    @Override
    public List<InformationBoardVo> getTodayBest() {
        return boardMapper.getTodayBest();
    }
    
    //상단 공지 2개  가져오기 김세영
    @Override
    public List<InformationBoardVo> getLatestAnnounce() {
        return boardMapper.getLatestAnnounce();
    }
    
    // 김세영 글등록
	@Override
	public Long write(InformationBoardVo vo) {
		System.out.println("글등록 김세영");
		int count = boardMapper.insertSelectKeyKsy(vo);
		
		// 첨부파일 insert
		List<AttachBoardDto> list = vo.getAttachList();
		if (list != null && list.size() > 0) {
			list.forEach(dto -> {
				dto.setBoardNo(vo.getBoardNo());
				attachMapper.insertKsy(dto);
			});
		}
		
		
		if (count > 0) {
			return vo.getBoardNo();
		}
		return 0L;
	}
	// 김세영 글보기
	@Override
	public InformationBoardVo get(Long bno) {
	    System.out.println("get...");
	    InformationBoardVo vo = boardMapper.selectByBnoKsy(bno);
	    
	    
	    return vo;
	}

	@Override
	public Long modify(InformationBoardVo vo) {
	    System.out.println("글수정 김세영");
	    int count = boardMapper.modifySelectKeyKsy(vo);
	    // 첨부파일 처리
	    List<AttachBoardDto> attachList = vo.getAttachList();
	    if (attachList != null && attachList.size() > 0) {
	        attachList.forEach(dto -> {
	            dto.setBoardNo(vo.getBoardNo());
	            attachMapper.insertKsy(dto);
	        });
	    }
	    
	    if (count > 0) {
	        return vo.getBoardNo();
	    }
	    return 0L;
	}

	
	@Override
	public void delete(Long boardNo) {
	    boardMapper.deleteRepliesByBoardNo(boardNo); // 댓글 삭제
	    boardMapper.deleteAttachByBoardNo(boardNo);  // 첨부파일 삭제
	    boardMapper.deleteBoardLikesByBoardNo(boardNo); // 게시글의 좋아요 삭제
	    boardMapper.deleteBoardByBoardNo(boardNo);   // 게시글 삭제
	}
	
	@Override
	public boolean modifyReadcnt(Long boardNo) {
		int count = boardMapper.updateCnt(boardNo);
		return (count == 1) ? true : false;
	}
	


}
