package com.traveler.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/budget/budget", method=RequestMethod.GET)
	public String budget(Model model,HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		List<PlannerVO> planner = plannerService.getAllPlanner(member.getUserId());
		
		model.addAttribute("allPlan",planner);
				
		return "/budget/budget";
	}
	
	@ResponseBody
	@RequestMapping(value="budget/load/userplan",method=RequestMethod.GET)
	public List<BudgetVO> getUserPlan(@RequestParam(value="planNo") int planNo, HttpSession session ) throws Exception{
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		List<BudgetVO> budget = budgetService.getUserBudgetFromPlanNo(planNo, member.getUserId());
		log.info(budget.size());
		if(budget.size() < 1 ) {
			List<UserPlanVO> schedule = planService.getUserScheduleFromPlanNo(planNo, member.getUserId());
			budget = budgetService.getUserBudgetFromSchedule(schedule);
		}
		
		log.info(budget);
		return budget;
	}

	@ResponseBody
	@RequestMapping(value="budget/save/budget",method=RequestMethod.POST)
	public String saveBudget(@RequestBody Map<String,Object> trans, HttpSession session) {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("planNo : "+trans.get("planNo"));
		log.info("total : "+trans.get("total"));
		log.info("transactions : "+trans.get("transactions"));
		
		List<BudgetVO> budget = budgetService.convertJSONintoBudget(trans, member.getUserId());
		log.info(budget);
		
		for(int i=0;i<budget.size();i++) {
			boolean result = budgetService.saveBudget(budget.get(i));
			if(result) log.info("budget is saved");
			else log.info("budget is not saved");
		}
		return "\"저장을 완료했습니다.\"";
	}
}
