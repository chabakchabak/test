package com.chabakchabak.www.lee.service.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chabakchabak.www.lee.domain.reply.ReplyInsertDto;
import com.chabakchabak.www.lee.domain.reply.ReplyListDto;
import com.chabakchabak.www.lee.domain.reply.ReplyUpdateDto;
import com.chabakchabak.www.lee.mapper.board.BoardMapper;
import com.chabakchabak.www.lee.mapper.reply.ReplyMapper;
import com.chabakchabak.www.lee.mapper.user.UserMapper;

import lombok.extern.log4j.Log4j;

@Service("leeReplyService")
@Log4j
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private UserMapper userMapper;
	
	private final int POINT = 2;
	
	// 댓글달기
	@Transactional
	@Override
	public boolean registerReply(ReplyInsertDto dto) {
		System.out.println("reply service......................");
		System.out.println("registerReply......................");
		System.out.println("dto : " + dto);
		//댓글 등록
		int insertReplyResult = replyMapper.insertReply(dto);
		if(insertReplyResult==0) {
			System.out.println("댓글 등록 실패.......");
			return false;
		}
		System.out.println("댓글 등록 성공.......");
		// 게시글에 댓글 수 업데이트
		int boardno = dto.getBoardno();
		int updateReplyCountResult = boardMapper.updateReplyCount(boardno);
		if(updateReplyCountResult==0) {
			System.out.println("게시글에 댓글 수 업데이트 실패.......");
			return false;
		}
		System.out.println("게시글에 댓글 수 업데이트 성공.......");
		//포인트
		String userid = dto.getUserid();
		System.out.println("userid : " + userid);
		
		int updatePointResult = userMapper.updatePoint(userid, POINT);
		if(updatePointResult==0) {
			System.out.println("포인트 등록 실패.......");
			return false;
		}
		System.out.println("포인트 등록 성공.......");
		System.out.println("댓글 등록 성공..............");
		return true;
	}

	// 댓글 list 가져오기
	@Override
	public List<ReplyListDto> getList(int boardno) {
		log.info("replyservice, getList..............");
		List<ReplyListDto> list = replyMapper.getList(boardno);
		for(ReplyListDto dto : list) {
			String userid = dto.getUserid();
			String profile = userMapper.getProfileImg(userid);
			dto.setProfile(profile);
			log.info("profile : " + profile);
			log.info("profile : " + profile);
		}
		return list;
	}

	// 댓글 삭제 댓글 번호로
	@Override
	public boolean delete(int replyno, String userid) {
		int result = replyMapper.delete(replyno);
		//포인트
		userMapper.updatePoint(userid, -POINT);
		if(result>0) {return true;};
		return false;
	}
	// 댓글 삭제 게시번호로
	@Override
	public boolean deleteByBoardNo(int boardno, String userid) {
		List<Integer> replynoList = replyMapper.getReplynoListWithBoardno(boardno);
		for(int replyno : replynoList) {
			boolean result = delete(replyno, userid);
			if(result==false) {
				return false;
			}
		}
		
		//포인트
		int result = userMapper.updatePoint(userid, -POINT);
		if(result==0) {return false;};
		return true;
	}

	// 댓글 수정
	@Override
	public boolean update(ReplyUpdateDto dto) {
		int result = replyMapper.update(dto);
		if(result>0) {return true;};
		return false;
	}

//	@Override
//	public boolean like(ReplyLikeDto dto) {
//		// TODO Auto-generated method stub
//		return false;
//	}




}
