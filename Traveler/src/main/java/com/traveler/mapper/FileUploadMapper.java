package com.traveler.mapper;

import java.util.Map;

public interface FileUploadMapper {
	public int insertUserImg(Map<String,Object> fileInfo);
	
	public String readUserImgPath(String userId);
	
	public int deleteUserImg(String userId);
	
	public int readUserImgCount(String userId);
	
	public String readUserImgName(String userId);
}
