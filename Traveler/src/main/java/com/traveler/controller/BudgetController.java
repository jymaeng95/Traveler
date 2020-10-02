package com.traveler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveler.api.SpotAPI;
import com.traveler.domain.BudgetVO;
import com.traveler.domain.MemberVO;
import com.traveler.domain.PlannerVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.BudgetService;
import com.traveler.service.PlannerService;
import com.traveler.service.UserPlanService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class BudgetController {

	private PlannerService plannerService;
	private UserPlanService planService;
	private BudgetService budgetService;
	private SpotAPI spot;
	 
	@RequestMapping(value="/budget/index", method=RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		List<PlannerVO> planner = new ArrayList<>();
		List<BudgetVO> allBudget = budgetService.getAllMemberPlanNoIsPublicYes();
		log.info("budget/index budget: "+allBudget);
		for(BudgetVO bg : allBudget) 
			planner.add(plannerService.getAllPlannerFromPlanNo(bg.getPlanNo()));
		model.addAttribute("allBudget",allBudget);
		model.addAttribute("allPlan", planner);
		log.info("budget/index planner: "+planner);
		return "/budget/index";
	}
	
	@RequestMapping(value="/budget/budget", method=RequestMethod.GET)
	public String budget(Model model,HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		List<PlannerVO> planner = plannerService.getAllPlanner(member.getUserId());
		log.info(planner.size());
		
		model.addAttribute("allPlan",planner);
		
		return "/budget/budget";
	}

	@ResponseBody
	@RequestMapping(value="budget/load/userplan",method=RequestMethod.GET)
	public List<Map<String,Object>> getUserPlan(@RequestParam(value="planNo") int planNo, HttpSession session ) throws Exception{
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		Map<String,Object> map;
		List<BudgetVO> budget = budgetService.getUserBudgetFromPlanNo(planNo, member.getUserId());
		List<UserPlanVO> schedule = planService.getUserScheduleFromPlanNo(planNo, member.getUserId());
		List<Map<String,Object>> result = new ArrayList<>();
		log.info(budget.size());
		if(budget.size() < 1 ) 
			budget = budgetService.getUserBudgetFromSchedule(schedule);
		log.info(budget.size());
		log.info(schedule);
		log.info(budget);
		for(BudgetVO bg : budget) {
			map = new HashMap<>();
			map.put("budget",bg);
			map.put("fee","");
			for(UserPlanVO sch : schedule) {
				if(bg.getTitle().equals(sch.getTitle())) {
					if(sch.getContentId() != null || sch.getContentTypeId() !=null) {
						String feeInfo = spot.getFeeInfo(sch.getContentId(), sch.getContentTypeId());
						if(feeInfo != null)
							map.put("fee",feeInfo);
					}
				}
			}
			result.add(map);
		}
		//14(usefee) 15(usetimefestival) 28(usefeeleports)
		log.info(result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value="budget/save/budget",method=RequestMethod.POST)
	public String saveBudget(@RequestBody Map<String,Object> trans, HttpSession session) throws ParseException {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("planNo : "+trans.get("planNo"));
		log.info("total : "+trans.get("total"));
		log.info("transactions : "+trans.get("transactions"));

		List<BudgetVO> budget = budgetService.convertMapIntoBudget(trans, member.getUserId());
		log.info(budget);

		for(int i=0;i<budget.size();i++) {
			boolean result = budgetService.saveBudget(budget.get(i));
			if(result) log.info("budget is saved");
			else log.info("budget is not saved");
		}
		return "\"저장을 완료했습니다.\"";
	}

	@ResponseBody
	@RequestMapping(value="budget/load/date", method=RequestMethod.GET)
	public UserPlanVO getUserPlanDate(@RequestParam(value="planNo") int planNo, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");

		return planService.getUserPlanDate(member.getUserId(),planNo);
	}
}
