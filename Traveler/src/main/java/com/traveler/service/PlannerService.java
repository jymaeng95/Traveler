package com.traveler.service;

import java.util.List;

import com.traveler.domain.PlannerVO;

public interface PlannerService {
	public boolean savePlanner(PlannerVO planner);
	
	public int getPlanNo();
	
	public List<PlannerVO> getAllPlanner(String userId);
	
	public PlannerVO getPlanner(PlannerVO planner);
}
