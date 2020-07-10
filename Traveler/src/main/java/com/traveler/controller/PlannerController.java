package com.traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class PlannerController {
	
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
	
	@RequestMapping(value="/plan/make_plan", method=RequestMethod.GET)
	public String makePlan(Model model) {
		log.info("make_plan");
		return "/plan/make_plan";
	}
	
}
