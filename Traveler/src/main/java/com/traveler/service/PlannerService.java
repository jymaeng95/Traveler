package com.traveler.service;

import java.util.List;

import com.traveler.domain.PlannerVO;

public interface PlannerService {
	public boolean savePlanner(PlannerVO planner);
	
	public int getPlanNo();
	
	public List<PlannerVO> getAllPlanner(String userId);
	
	public PlannerVO getPlanner(PlannerVO planner);
	
	public boolean updatePlannerImg(PlannerVO planner);
	
	public boolean updatePlanner(PlannerVO planner, String userId);
	
	public PlannerVO getAllPlannerFromPlanNo(int planNo);
	
	public int isExist(String userId);
	
	public boolean deletePlanner(String userId, int planNo);
}
