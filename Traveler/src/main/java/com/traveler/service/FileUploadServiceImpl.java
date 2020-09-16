package com.traveler.service;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public Map<String,Object> getFileInfo(MultipartHttpServletRequest request, String userId) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		return fileUtil.getFileInfo(request, userId);
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
}
