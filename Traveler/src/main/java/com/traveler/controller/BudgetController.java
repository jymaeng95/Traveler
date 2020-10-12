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
import com.traveler.domain.PageVO;
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
	public String index(Model model, HttpSession session,@RequestParam(value="pageNum", defaultValue = "1")String pageNo) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		List<PlannerVO> planner = new ArrayList<>();
		List<UserPlanVO> schedule = new ArrayList<>();
		List<BudgetVO> pagingBudget = budgetService.getBudgetIndexPaging(pageNo);
		for(BudgetVO bg : pagingBudget) {
			planner.add(plannerService.getAllPlannerFromPlanNo(bg.getPlanNo()));
			schedule.add(planService.getPlanDate(bg.getPlanNo()));
		}
		model.addAttribute("pageMaker",new PageVO(pageNo,budgetService.getCountBudgetIsPublic(),5));
		model.addAttribute("allSchedule",schedule);
		model.addAttribute("allPlan", planner);
		model.addAttribute("countUserPlan",plannerService.isExist(member.getUserId()));
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

	@RequestMapping(value="/budget/recommend", method=RequestMethod.GET)
	public String recommendBudget(Model model,@RequestParam(value="pageNum",defaultValue="1")String pageNo, @RequestParam(value="planTotalDate") String planTotalDate,HttpSession session) {
		List<BudgetVO> budget = budgetService.getPlanNoEqualTotalDate(planTotalDate,pageNo);
		List<PlannerVO> planner = new ArrayList<>();
		for(BudgetVO bg : budget) 
			planner.add(plannerService.getAllPlannerFromPlanNo(bg.getPlanNo()));

		log.info(planner);
		//null리턴 경우 
		BudgetVO maxBudget = budgetService.getPlanNoRecommendMaxTotal(planTotalDate);
		BudgetVO minBudget = budgetService.getPlanNoRecommendMinTotal(planTotalDate);
		if(planner.size() > 1) {
			if(maxBudget != null) {
				PlannerVO maxPlan = plannerService.getAllPlannerFromPlanNo(maxBudget.getPlanNo());
				model.addAttribute("maxBudget",maxBudget);
				model.addAttribute("maxPlan",maxPlan);
			}
			if(minBudget != null) {
				PlannerVO minPlan= plannerService.getAllPlannerFromPlanNo(minBudget.getPlanNo());
				model.addAttribute("minBudget",minBudget);
				model.addAttribute("minPlan",minPlan);
			}
		} else if(planner.size() == 1) {
			if(maxBudget != null) {
				PlannerVO maxPlan = plannerService.getAllPlannerFromPlanNo(maxBudget.getPlanNo());
				model.addAttribute("maxBudget",maxBudget);
				model.addAttribute("maxPlan",maxPlan);
			}
		}
		model.addAttribute("pageMaker",new PageVO(pageNo,budgetService.getCountBudgetFromTotalDate(planTotalDate),5));
		model.addAttribute("rcmPlanSize",planner.size());
		model.addAttribute("allPlan", planner);
		model.addAttribute("planTotalDate",planTotalDate);

		return "/budget/recommend";
	}

	@ResponseBody
	@RequestMapping(value="/budget/load/userplan",method=RequestMethod.GET)
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
	@RequestMapping(value="/budget/save/budget",method=RequestMethod.POST)
	public String saveBudget(@RequestBody Map<String,Object> trans, HttpSession session) throws ParseException {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("planNo : "+trans.get("planNo"));
		log.info("total : "+trans.get("total"));
		log.info("totalDate : "+trans.get("planTotalDate"));
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
	@RequestMapping(value="/budget/load/date", method=RequestMethod.GET)
	public UserPlanVO getUserPlanDate(@RequestParam(value="planNo") int planNo, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");

		return planService.getUserPlanDate(member.getUserId(),planNo);
	}

	@ResponseBody
	@RequestMapping(value="/budget/index/graph" ,method=RequestMethod.GET)
	public Map<String,Object> getAllBudget() {
		List<Map<String,Object>> budgetMap = budgetService.getIsPublicBudget();
		List<Map<String,Object>> catMap = budgetService.getIsPublicCat();

		Map<String,Object> returnData = new HashMap<>();
		returnData.put("catData", catMap);
		returnData.put("budgetData", budgetMap);
		return returnData;
	}
	
	@ResponseBody
	@RequestMapping(value="/budget/recommend/graph" ,method=RequestMethod.GET)
	public Map<String,Object> getRecommendGraph(@RequestParam(value="planTotalDate") String planTotalDate) {
		List<Map<String,Object>> budgetMap = budgetService.getIsPublicBudgetFromTotalDate(planTotalDate);
		List<Map<String,Object>> catMap = budgetService.getIsPublicCatFromTotalDate(planTotalDate);

		Map<String,Object> returnData = new HashMap<>();
		returnData.put("catData", catMap);
		returnData.put("budgetData", budgetMap);
		return returnData;
	}
	
	@RequestMapping(value="/budget/read", method=RequestMethod.GET)
	public String readPlanNoBudget(Model model, @RequestParam(value="planNo") int planNo, @RequestParam(value="recommend", defaultValue="일반") String recommend) {
		List<BudgetVO> budget = budgetService.getUserBudgetFromPlanNo(planNo);
		PlannerVO planner = plannerService.getAllPlannerFromPlanNo(planNo);
		
		log.info("BUDGET from /budget/read : "+ budget);
		log.info("PLANNER from /budget/read : "+ planner);
		
		model.addAttribute("budget",budget);
		model.addAttribute("planner",planner);
		model.addAttribute("planTotalDate",budget.get(0).getPlanTotalDate());
		model.addAttribute("recommend", recommend);
		return "/budget/read";
	}
}
