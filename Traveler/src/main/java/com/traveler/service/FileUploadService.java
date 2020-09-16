package com.traveler.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileUploadService {
	public Map<String,Object> getFileInfo(MultipartHttpServletRequest request, String userId) throws IllegalStateException, IOException;

	public String getUserImgPath(String userId);
	
	public boolean insertUserImg(Map<String,Object> fileInfo);
	
	public boolean deleteUserImg(String userId);
	
	public boolean getUserImgCount(String userId);
	
	public boolean deleteFileFromServer(String path);
	
	public String getUserFileName(String userId);
}
