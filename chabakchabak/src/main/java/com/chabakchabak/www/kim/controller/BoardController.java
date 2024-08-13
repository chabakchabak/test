package com.chabakchabak.www.kim.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chabakchabak.www.kim.domain.AttachBoardDto;
import com.chabakchabak.www.kim.domain.InformationBoardVo;
import com.chabakchabak.www.kim.domain.InformationCriteria;
import com.chabakchabak.www.kim.domain.InformationPageDto;
import com.chabakchabak.www.kim.mapper.AttachMapper;
import com.chabakchabak.www.kim.service.BoardService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller("kimBoardController")
@RequestMapping("/Kim/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
    private AttachMapper attachMapper;

	// 정보공유 게시판 (김세영)
	@GetMapping("/info")
	public void informationBoard(Model model, InformationCriteria criteria,
	        @RequestParam(value = "type", required = false) String type,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(value = "sort", required = false) String sort, 
	        @RequestParam(value = "category", required = false) String category) {
		
		System.out.println("Criteria: " + criteria.toString());
		
		// 조건이 변경될 때 페이지 번호를 1로 리셋
	    if (category != null && !category.equals(criteria.getCategory())) {
	        criteria.setPageNum(1);
	    }

		
	    if (criteria.getPageNum() == 0) {
	        criteria.setPageNum(1);
	    }
	    if (criteria.getAmount() == 0) {
	        criteria.setAmount(10);
	    }
	    
	    
	   
	    criteria.setType(type);
	    criteria.setKeyword(keyword);
	    criteria.setSort(sort);
	    criteria.setCategory(category);

	    List<InformationBoardVo> list = boardService.getListKsy(criteria);
	    int total = boardService.getTotalKsy(criteria);
	    InformationPageDto pageMaker = new InformationPageDto(criteria, total);
	    model.addAttribute("list", list);
	    model.addAttribute("pageMaker", pageMaker);
	    model.addAttribute("criteria", criteria);
	    
	    // 주간 베스트 게시물 리스트 추가
	    List<InformationBoardVo> weeklyBestList = boardService.getWeeklyBest();
        model.addAttribute("weeklyBestList", weeklyBestList);
        
        // 오늘의 BEST 게시물 리스트 추가
        List<InformationBoardVo> todayBestList = boardService.getTodayBest();
        model.addAttribute("todayBestList", todayBestList);
        
        // 상단 공지 2개 가져오기
        List<InformationBoardVo> latestAnnounce = boardService.getLatestAnnounce();
        model.addAttribute("latestAnnounce", latestAnnounce);
        
	}

	// **김세영 글쓰기 폼**
	@GetMapping("/write")
	public void write(Model model) {
		
		// 주간 베스트 게시물 리스트 추가
	    List<InformationBoardVo> weeklyBestList = boardService.getWeeklyBest();
        model.addAttribute("weeklyBestList", weeklyBestList);
	}
	
	// 김세영 글쓰기 처리
	@PostMapping("/write")
	public String write(InformationBoardVo vo, @RequestParam("category") String category, RedirectAttributes rttr, HttpSession session) { // 수정된 부분: HttpSession 추가
		
		// 선택한 카테고리를 제목 앞에 붙임
	    String fullTitle = "[" + category + "] " + vo.getTitle();
	    vo.setTitle(fullTitle);
		
        Long boardNo = boardService.write(vo);
        
        @SuppressWarnings("unchecked")
		List<AttachBoardDto> ckeditorImages = (List<AttachBoardDto>) session.getAttribute("ckeditorImages"); // 추가된 부분
		// 한번쓰고 버릴꺼라서 리다이렉트 (일회성)
		
        if (ckeditorImages != null) {
            for (AttachBoardDto dto : ckeditorImages) {
                dto.setBoardNo(boardNo);
                attachMapper.insertKsy(dto);
            }
            session.removeAttribute("ckeditorImages"); // 추가된 부분
        }

        rttr.addFlashAttribute("resultWriter", boardNo);
        return "redirect:/Kim/board/read?boardNo=" + boardNo;
	}
	
	// 김세영 글읽기
	@GetMapping("/read")
	public String read(@RequestParam("boardNo") Long boardNo, @ModelAttribute InformationCriteria criteria, Model model, 
			@RequestParam(value = "type", required = false) String type,
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(value = "sort", required = false) String sort, 
	        @RequestParam(value = "category", required = false) String category) {
		InformationBoardVo boardVo = boardService.get(boardNo);
		model.addAttribute("boardVo", boardVo);
		
		criteria.setType(type);
	    criteria.setKeyword(keyword);
	    criteria.setSort(sort);
	    criteria.setCategory(category);
	    
	    boolean result = boardService.modifyReadcnt(boardNo);
	    boardVo.setViews(boardVo.getViews() + 1);
		log.info("조회수 증가됨");
        
        // 게시판 리스트 불러오기
	    List<InformationBoardVo> list = boardService.getListKsy(criteria);
	    int total = boardService.getTotalKsy(criteria);
	    InformationPageDto pageMaker = new InformationPageDto(criteria, total);
	    model.addAttribute("list", list);
	    model.addAttribute("pageMaker", pageMaker);
	    model.addAttribute("criteria", criteria);
        
	    // 주간 베스트 게시물 리스트 추가
	    List<InformationBoardVo> weeklyBestList = boardService.getWeeklyBest();
	    model.addAttribute("weeklyBestList", weeklyBestList);
	    
        return "Kim/board/read";
	}
	
	// 김세영 글 수정페이지로 이동
	@GetMapping("/modifyForm")
	public String modifyForm(@RequestParam("boardNo") Long boardNo, Model model) {
	    
		InformationBoardVo boardVo = boardService.get(boardNo);
		
		// 제목에서 카테고리 추출하기 (제목의 맨 앞에 있는 카테고리만 추출)
        String title = boardVo.getTitle();
        String category = "";
        String modifiedTitle = title;  // 카테고리를 제거한 제목
        if (title.startsWith("[") && title.indexOf("]") > 1) {
            category = title.substring(1, title.indexOf("]"));
            modifiedTitle = title.substring(title.indexOf("]") + 1).trim();
        }
        
     // 수정된 제목을 boardVo에 설정
        boardVo.setTitle(modifiedTitle);
	    
	    
	    model.addAttribute("boardVo", boardVo);
	    model.addAttribute("category", category); 
	    
	 // 주간 베스트 게시물 리스트 추가
	    List<InformationBoardVo> weeklyBestList = boardService.getWeeklyBest();
	    model.addAttribute("weeklyBestList", weeklyBestList);
	    return "Kim/board/modify";
	}
	
	
	
	@PostMapping("/modify")
	public String modify(InformationBoardVo vo, @RequestParam("category") String category, RedirectAttributes rttr, HttpSession session) {
		
		System.out.println("Content: " + vo.getContent());
		
	    // 현재 제목에서 기존의 대괄호와 카테고리를 제거
	    String title = vo.getTitle();
	    if (title.startsWith("[") && title.contains("]")) {
	        title = title.substring(title.indexOf("]") + 1).trim();
	    }

	    // 새로운 카테고리와 제목 결합
	    String fullTitle = category + " " + title;
	    vo.setTitle(fullTitle);
	    
	    // 글 수정 처리
	    Long boardNo = boardService.modify(vo);
	    
	    // CKEditor 이미지 파일 처리
	    @SuppressWarnings("unchecked")
		List<AttachBoardDto> ckeditorImages = (List<AttachBoardDto>) session.getAttribute("ckeditorImages");
	    if (ckeditorImages != null) {
	        for (AttachBoardDto dto : ckeditorImages) {
	            dto.setBoardNo(boardNo);  // 게시글 번호 설정
	            attachMapper.insertKsy(dto);  // 이미지 정보 삽입
	        }
	        session.removeAttribute("ckeditorImages");  // 세션에서 이미지 정보 제거
	    }
	    
	    // 수정 결과를 리다이렉트 속성에 추가하고, 상세보기 페이지로 리다이렉트
	    rttr.addFlashAttribute("resultModify", boardNo);
	    return "redirect:/Kim/board/read?boardNo=" + boardNo;
	}
	
    // 김세영 글 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam Long boardNo, RedirectAttributes rttr) {
        boardService.delete(boardNo);
        rttr.addFlashAttribute("resultDelete", boardNo);
        return "redirect:/Kim/board/info"; // 삭제 후 게시글 목록 페이지로 리디렉션
    }

}
