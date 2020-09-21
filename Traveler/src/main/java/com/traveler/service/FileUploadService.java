package com.traveler.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.traveler.domain.PlannerVO;

public interface FileUploadService {
	public Map<String,Object> getFileInfo(MultipartFile file, String path, String userId) throws IllegalStateException, IOException;

	public String getUserImgPath(String userId);
	
	public boolean insertUserImg(Map<String,Object> fileInfo);
	
	public boolean deleteUserImg(String userId);
	
	public boolean getUserImgCount(String userId);
	
	public boolean deleteFileFromServer(String path);
	
	public String getUserFileName(String userId);
	
	public String getPlanImgPath(PlannerVO planner);
	
	public boolean insertPlanImg(Map<String,Object> fileInfo);
	
	public boolean deletePlanImg(PlannerVO planner);
	
	public boolean getPlanImgCount(PlannerVO planner);

	public String getPlanFileName(PlannerVO planner);
}
