package com.traveler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveler.api.SpotAPI;
import com.traveler.domain.BookmarkVO;
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
	
	@RequestMapping(value="/plan/plan", method =RequestMethod.GET)
	public String plannerIndex(Model model) {
		log.info("planner_index");
		return "/plan/plan";
		
	}
	@RequestMapping(value="/plan/create", method=RequestMethod.GET)
	public String makePlan(Model model, String plan_title, String plan_date, String total_date) throws Exception {
		log.info("make_plan");
		model.addAttribute("plan_title", plan_title);
		model.addAttribute("plan_date", plan_date);
		model.addAttribute("total_date", total_date);
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
	
	@ResponseBody
	@RequestMapping(value="/plan/tmap/keyword", method=RequestMethod.GET)
	public List<SpotVO> getKeywordFromTmap(SpotVO spotVO, String keyword) throws Exception{
		log.info("T-map/controller" + spotVO + "keyword : "+keyword);
		return spot.getKeywordFromPOI(spotVO, keyword);
	}
}
