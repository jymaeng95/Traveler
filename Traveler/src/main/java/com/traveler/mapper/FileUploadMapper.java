package com.traveler.mapper;

import java.util.Map;

import com.traveler.domain.PlannerVO;

public interface FileUploadMapper {
	public int insertUserImg(Map<String,Object> fileInfo);
	
	public String readUserImgPath(String userId);
	
	public int deleteUserImg(String userId);
	
	public int readUserImgCount(String userId);
	
	public String readUserImgName(String userId);
	
	public int insertPlanImg(Map<String, Object> fileInfo);
	
	public String readPlanImgPath(PlannerVO planner);
	
	public int deletePlanImg(PlannerVO planner);
	
	public int readPlanImgCount(PlannerVO planner);
	
	public String readPlanImgName(PlannerVO planner);
}
