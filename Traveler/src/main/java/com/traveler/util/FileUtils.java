package com.traveler.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;

@Component("fileUtils")
@Log4j
public class FileUtils {
	public Map<String,Object> getFileInfo(MultipartFile file, String path,String userId) throws IllegalStateException, IOException{
		Map<String,Object> fileInfo = new HashMap<String,Object>();
		
		log.info("fileutils" + path);
		log.info("file name : "+file.getOriginalFilename());
		
		File dir = new File(path); 
		if(!dir.exists()) { 
			dir.mkdirs(); 
		} // 파일 업로드
		
		if(!file.isEmpty()) {
			String orgFileName = file.getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = getRandomString() + orgFileExtension;
			
			dir = new File(path+"/"+saveFileName);
			file.transferTo(dir);
			
			fileInfo.put("userId", userId);
			fileInfo.put("org_filename",orgFileName);
			fileInfo.put("save_filename",saveFileName);
			fileInfo.put("full_path",path+"/"+saveFileName);
			fileInfo.put("file_size",file.getSize());
		}
		return fileInfo;
	}

	private String getRandomString() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public boolean deleteFile(String path) {
		File file = new File(path);
		if(file.exists())	{
			file.delete();
			return true;
		}
		else return false;
	}
}
