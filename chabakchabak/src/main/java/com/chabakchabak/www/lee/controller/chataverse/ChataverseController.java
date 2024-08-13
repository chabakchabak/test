package com.chabakchabak.www.lee.controller.chataverse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.teamproject.www.lee.controller.chataverse.ChataverseController;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/lee/chataverse/*")
@Log4j
//@Qualifier("leeReplyService")
public class ChataverseController {
	
	@GetMapping("/selectGender")
	public void selectGender() {
		
	}
	
	@GetMapping("/choiceGender/{gender}")
	public String choiceGender(@PathVariable("gender") String gender) {
		log.info("gender : " + gender);
		return "redirect:/lee/chataverse/choiceRegion";
	}
	
	@GetMapping("/choiceRegion")
	public void choiceRegion(){
		
	}
	
	@GetMapping("/play/{region}")
	public String play(@PathVariable("region") String region) {
		log.info("play......................");
		log.info("region : " + region);
		return "lee/chataverse/play";
	}
}
