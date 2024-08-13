package com.chabakchabak.www.jang.controller.board;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Jang/board/market/*")
@Qualifier("JangMarketService")
public class MarketController {
	
	@GetMapping("/list")
	public String list() {
		return "Jang/board/market/total";
	}
}
