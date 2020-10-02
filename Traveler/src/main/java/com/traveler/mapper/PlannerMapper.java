package com.traveler.mapper;

import java.util.List;

import com.traveler.domain.PlannerVO;

public interface PlannerMapper {
	public int insertNewPlanner(PlannerVO planner);
	
	public int readPlanNo();
	
	public PlannerVO readPlanner(PlannerVO planner);
	
	public List<PlannerVO> readAllPlanner(String userId);
	
	public int deletePlanner(PlannerVO planner);
	
	public int updatePlanner(PlannerVO planner);
	
	public int updatePlannerImg(PlannerVO planner);
	
	public PlannerVO readAllPlannerFromPlanNo(int planNo);
	
	public int isExist(String userId);
}
