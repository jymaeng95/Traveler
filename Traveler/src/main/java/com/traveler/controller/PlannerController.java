package com.traveler.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.traveler.api.SpotAPI;
import com.traveler.domain.BookmarkVO;
import com.traveler.domain.MemberVO;
import com.traveler.domain.SpotVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.BookmarkServiceImpl;
import com.traveler.service.UserPlanService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Controller
@Log4j
@AllArgsConstructor
public class PlannerController {
	private BookmarkServiceImpl b_service;
	private UserPlanService planService;
	private SpotAPI spot;
	
	@RequestMapping(value="/plan/my_plan", method=RequestMethod.GET)
	public String myPlan(Model model) {
		
		log.info("my_plan");
		return "/plan/my_plan";
	}
	
	@RequestMapping(value="/plan/plandetail", method=RequestMethod.POST)
	public String myPlan2(@RequestParam(value="planData") String data,Model model,HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(data);
		List<UserPlanVO> plan = planService.convertUserPlan(data, member.getUserId());
		log.info(plan);
		for(int i=0;i<plan.size();i++) {
			boolean result = planService.saveUserPlan(plan.get(i));
			if (result) log.info("plan is Saved");
			else log.info("plan is Not Saved");
		}
		log.info(data);
		
		model.addAttribute("planLength",plan.size());
		model.addAttribute("planList",JSONArray.fromObject(plan));
		return "/plan/plandetail";
	}
	
	@RequestMapping(value="plan/save/schedule", method=RequestMethod.POST)
	public String savePlan(@RequestParam(value="schedule") String data, Model model, RedirectAttributes rttr) throws Exception {
		log.info(data);
		List<UserPlanVO> schedule = planService.convertSchedule(data);
		
		for(int i=0;i<schedule.size();i++) {
			boolean result = planService.saveSchedule(schedule.get(i));
			if (result) log.info("schedule is Saved");
			else log.info("schedule is Not Saved");
		}
		log.info(data);
//		
		return "redirect:/plan/plandetail";
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
		model.addAttribute("planNo",planService.getTotalCountPlan());
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
