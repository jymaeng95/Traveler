package com.traveler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.traveler.domain.PlannerVO;
import com.traveler.domain.SpotVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.BookmarkServiceImpl;
import com.traveler.service.PlannerService;
import com.traveler.service.UserPlanService;
import com.traveler.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Controller
@Log4j
@AllArgsConstructor
public class PlannerController {
	private BookmarkServiceImpl b_service;
	private UserPlanService planService;
	private PlannerService plannerService;
	private SpotAPI spot;

	@RequestMapping(value="/plan/my_plan", method=RequestMethod.GET)
	public String myPlan(Model model) {

		log.info("my_plan");
		return "/plan/my_plan";
	}


	@RequestMapping(value="/plan/plan/detail", method=RequestMethod.GET)
	public String myPlan2(@RequestParam(value="planNo") int planNo, @RequestParam(value="when") String when,Model model,HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(planNo);

		String isModify = "N";

		PlannerVO planner = new PlannerVO();
		planner.setPlanNo(planNo);
		planner.setUserId(member.getUserId());
		planner = plannerService.getPlanner(planner);

		List<UserPlanVO> plan = new ArrayList<>();
		if (when.equals("plan")) {
			plan = planService.getUserPlanFromPlanNo(planNo,member.getUserId());
			log.info("plan data : " + plan);
		}
		if (when.equals("schedule")) {
			plan = planService.getUserScheduleFromPlanNo(planNo, member.getUserId());
			log.info("schedule data : " + plan);
		}

		if (when.equals("modify")) {
			plan = planService.getUserScheduleFromPlanNo(planNo, member.getUserId());
			isModify = "Y";
			System.out.println(plan);
		}

		model.addAttribute("isModify",isModify);
		model.addAttribute("planner",planner);
		model.addAttribute("userId",member.getUserId());	
		model.addAttribute("planNo",planNo);
		model.addAttribute("planList",JSONArray.fromObject(plan));
		return "/plan/plandetail";
	}


	@RequestMapping(value="/plan/plan/readPlan", method=RequestMethod.GET) public
	String readPlan(@RequestParam(value="planNo") int planNo, Model
			model,HttpSession session) throws Exception { MemberVO member = (MemberVO)
			session.getAttribute("userInfo"); log.info(planNo);

			PlannerVO planner = new PlannerVO(); planner.setPlanNo(planNo);
			planner.setUserId(member.getUserId()); planner =
					plannerService.getPlanner(planner);

			List<UserPlanVO> plan = new ArrayList<>(); plan =
					planService.getUserScheduleFromPlanNo(planNo, member.getUserId());

			model.addAttribute("planner",planner);
			model.addAttribute("userId",member.getUserId());
			model.addAttribute("planNo",planNo);
			model.addAttribute("planList",JSONArray.fromObject(plan)); return
					"/plan/read"; }


	@ResponseBody
	@RequestMapping(value="/plan/delete", method=RequestMethod.POST,produces="application/text;charset=utf8")
	public String deletePlan(@RequestParam(value="planNo") int planNo, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(planNo);

		if(plannerService.deletePlanner(member.getUserId(),planNo )) {
			return "삭제완료";
		} 
		return null;
	}

	@RequestMapping(value="plan/save/plan",method=RequestMethod.POST)
	public String savePlan(@RequestParam(value="planData") String data,RedirectAttributes rttr,HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(data);
		List<UserPlanVO> plan = planService.convertUserPlan(data, member.getUserId());
		log.info(plan);
		System.out.println("123 "  + data);
		System.out.println(plan);
		for(int i=0;i<plan.size();i++) {
			boolean result = planService.saveUserPlan(plan.get(i));
			if (result) log.info("plan is Saved");
			else log.info("plan is Not Saved");
		}
		log.info(data);

		rttr.addAttribute("planNo",plan.get(0).getPlanNo());
		rttr.addAttribute("when","plan");
		return "redirect:/plan/plan/detail";
	}

	@RequestMapping(value="plan/save/schedule", method=RequestMethod.POST)
	public String savePlan(@RequestParam(value="schedule") String data, Model model, RedirectAttributes rttr,HttpSession session) throws Exception {
		log.info(data);
		List<UserPlanVO> schedule = planService.convertSchedule(data);

		planService.deleteUserSchedule(schedule.get(0));

		for(int i=0;i<schedule.size();i++) {
			boolean result = planService.saveSchedule(schedule.get(i));
			if (result) log.info("schedule is Saved");
			else log.info("schedule is Not Saved");
		}
		log.info(data);
		return "redirect:/plan/plan";
	}

	@RequestMapping(value="plan/save/modify",method=RequestMethod.POST)
	public String saveModify(@RequestParam(value="planData") String data,RedirectAttributes rttr,HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(data);

		List<UserPlanVO> newPlan = planService.convertUserPlan(data, member.getUserId());
		List<UserPlanVO> oldPlan = planService.getUserScheduleFromPlanNo(newPlan.get(0).getPlanNo(),member.getUserId());
		boolean isDiff = true;

		planService.deleteUserPlan(oldPlan.get(0));

		for(int i=0; i<newPlan.size(); i++) {
			isDiff = true;
			for(int j=0; j<oldPlan.size(); j++) {
				if(newPlan.get(i).getTitle().equals(oldPlan.get(j).getTitle())) {
					System.out.println("oldPlanTitle: " + oldPlan.get(j).getTitle());
					planService.saveUserModifyPlan(oldPlan.get(j));
					isDiff = false;
					break;
				} 
			}
			if(isDiff) {
				planService.saveUserPlan(newPlan.get(i));
			}
		}

		for(int i=0; i<oldPlan.size(); i++) {
			if(oldPlan.get(i).getIs_insertAfter().equals("Y")) {
				planService.saveSchedule(oldPlan.get(i));
			} 
		}

		rttr.addAttribute("planNo",oldPlan.get(0).getPlanNo());
		rttr.addAttribute("when","schedule");
		return "redirect:/plan/plan/detail";
	}

	@RequestMapping(value="plan/plan", method=RequestMethod.GET)
	public String planIndex(Model model, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("plan/index");
		List<Map<String,Object>> planInfo = new ArrayList<>();

		UserPlanVO dateInfo = new UserPlanVO();
		if(member != null) {
			List<PlannerVO> plan = plannerService.getAllPlanner(member.getUserId());
			log.info(plan);
			if(plan != null) {
				for(PlannerVO pl : plan) {
					Map<String,Object> data = new HashMap<String,Object>();
					dateInfo = planService.getUserPlanDate(member.getUserId(), pl.getPlanNo());
					if(dateInfo != null) {
						String palnStartDate = DateUtils.calcStartDate(dateInfo.getPlanDate(), dateInfo.getPlanDay());
						data.put("planStartDate",palnStartDate);
						data.put("planEndDate",DateUtils.calcDate(palnStartDate,dateInfo.getPlanTotalDate()));
						data.put("planTotalDate",dateInfo.getPlanTotalDate());
					}
					data.put("planInfo",pl);
					planInfo.add(data);
				}
			}
			model.addAttribute("resultData",planInfo);
		}
		//      session.removeAttribute("planner"); //뒤로갈때 session값이 남아있다는 문제 남음!
		return "/plan/plan";
	}

	@RequestMapping(value="/plan/create", method=RequestMethod.GET)
	public String makePlan(Model model, PlannerVO planner , String plan_date, String total_date,HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		planner.setUserId(member.getUserId());
		if(plannerService.savePlanner(planner)) log.info("planner Saved"+planner);
		else log.info("planner is not Saved" + planner);

		model.addAttribute("plan_title", planner.getPlanTitle());
		model.addAttribute("plan_date", plan_date);
		model.addAttribute("total_date", total_date);
		model.addAttribute("planNo",plannerService.getPlanNo());
		return "/plan/create";

	}   

	@RequestMapping(value="/plan/guide", method=RequestMethod.GET)
	public String guideSpot(SpotVO spotVO, Model model) throws Exception {
		log.info("guide");
		model.addAttribute("contentId", spotVO.getContentId());
		model.addAttribute("contentTypeId", spotVO.getContentTypeId());
		return "/plan/category/information";
	}

	@RequestMapping(value="/plan/modify",method=RequestMethod.GET)
	public String ModifyPlan(PlannerVO planner, Model model, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");

		planner.setUserId(member.getUserId());
		planner = plannerService.getPlanner(planner);
		List<UserPlanVO> plan = new ArrayList<>();
		plan = planService.getUserPlanFromPlanNo(planner.getPlanNo(), member.getUserId());
		String[] date = plan.get(0).getPlanDate().split("-");
		String plan_date = date[0] + "-" + date[1] + "-" +(Integer.parseInt(date[2])-(Integer.parseInt(plan.get(0).getPlanDay())-1));

		System.out.println(">> " + planService.getUserPlanFromPlanNo(planner.getPlanNo(), member.getUserId()));
		model.addAttribute("planList",JSONArray.fromObject(plan));
		model.addAttribute("plan_title", planner.getPlanTitle());
		model.addAttribute("plan_date", plan_date);
		model.addAttribute("total_date", plan.get(0).getPlanTotalDate());
		model.addAttribute("planNo",planner.getPlanNo());
		return "/plan/modify";
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

	@ResponseBody
	@RequestMapping(value="/plan/update/planner", method=RequestMethod.POST)
	public String updatePlanner(PlannerVO planner, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		if(plannerService.updatePlanner(planner,member.getUserId())) {
			log.info("planner is updated");
			return "success";
		}else log.info("planner is not updated");
		return "fail";
	}
}