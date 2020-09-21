package com.traveler.service;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.traveler.domain.PlannerVO;
import com.traveler.mapper.FileUploadMapper;
import com.traveler.util.FileUtils;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

	private FileUploadMapper mapper;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtil;
		
	@Override
	public boolean insertUserImg(Map<String,Object> fileInfo) {
		// TODO Auto-generated method stub
		return mapper.insertUserImg(fileInfo) > 0;
	}
	

	@Override
	public String getUserImgPath(String userId) {
		// TODO Auto-generated method stub
		return mapper.readUserImgPath(userId);
	}
	
	@Override
	public Map<String,Object> getFileInfo(MultipartFile file, String path, String userId) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
		return fileUtil.getFileInfo(file, path, userId);
	}


	@Override
	public boolean deleteUserImg(String userId) {
		// TODO Auto-generated method stub
		return mapper.deleteUserImg(userId) > 0;
	}


	@Override
	public boolean getUserImgCount(String userId) {
		// TODO Auto-generated method stub
		return mapper.readUserImgCount(userId) > 0;
	}

	@Override
	public boolean deleteFileFromServer(String path) {
		return fileUtil.deleteFile(path);
	}


	@Override
	public String getUserFileName(String userId) {
		// TODO Auto-generated method stub
		return mapper.readUserImgName(userId);
	}


	@Override
	public boolean insertPlanImg(Map<String, Object> fileInfo) {
		// TODO Auto-generated method stub
		return mapper.insertPlanImg(fileInfo) > 0;
	}


	@Override
	public boolean deletePlanImg(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.deletePlanImg(planner) > 0;
	}


	@Override
	public boolean getPlanImgCount(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.readPlanImgCount(planner) > 0;
	}


	@Override
	public String getPlanFileName(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.readPlanImgName(planner);
	}

	@Override
	public String getPlanImgPath(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.readPlanImgPath(planner);
	}
}
