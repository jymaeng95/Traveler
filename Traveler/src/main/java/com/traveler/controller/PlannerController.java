package com.traveler.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveler.api.SpotAPI;
import com.traveler.domain.BookmarkVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.SpotVO;
import com.traveler.service.BookmarkServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class PlannerController {
	private BookmarkServiceImpl b_service;
	private SpotAPI spot;
	
	@RequestMapping(value="/plan/my_plan", method=RequestMethod.GET)
	public String myPlan(Model model) {
		
		log.info("my_plan");
		return "/plan/my_plan";
	}
	
	@RequestMapping(value="/plan/my_plan2", method=RequestMethod.GET)
	public String myPlan2(Model model) {
		
		log.info("my_plan2");
		return "/plan/my_plan2";
	}
	
	@RequestMapping(value="/plan/plan_index", method =RequestMethod.GET)
	public String plannerIndex(Model model) {
		log.info("planner_index");
		return "/plan/plan_index";
		
	}
	@RequestMapping(value="/plan/create", method=RequestMethod.GET)
	public String makePlan(@RequestParam(value="pageNum", defaultValue="1") String pageNo, Model model,HttpSession session) throws Exception {
		log.info("make_plan");
//		BookmarkVO bookmark = 
//		ArrayList<BookmarkVO> b_list = b_service.getUserBookmark(bookmark);
		model.addAttribute("pageMaker", new PageVO(pageNo, spot.getTotalCount("", ""), 5));
		
		return "/plan/create";
	}
	
	@RequestMapping(value="/plan/guide", method=RequestMethod.GET)
	public String guideSpot(SpotVO spotVO, Model model) throws Exception {
		log.info("guide");
		model.addAttribute("contentId", spotVO.getContentId());
		model.addAttribute("contentTypeId", spotVO.getContentTypeId());
		return "/plan/category/information";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/plan/bookmark", method=RequestMethod.GET)
	public ArrayList<BookmarkVO> getBookmark(BookmarkVO bookmark) throws Exception{
		return b_service.getUserBookmark(bookmark);
	}
}
