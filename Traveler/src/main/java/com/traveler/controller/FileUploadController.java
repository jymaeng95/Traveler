package com.traveler.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.traveler.domain.MemberVO;
import com.traveler.service.FileUploadService;
import com.traveler.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class FileUploadController {
	
	private FileUploadService fileService;
	private final static String SERVER_FILE_PATH = "/resources/upload/img";
	@RequestMapping(value="/upload/user/img", method = RequestMethod.POST)
	public String uploadUserImg(MultipartHttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
//		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/img/user");
		// file , request 
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(request.getSession().getServletContext().getRealPath("/resources/upload/img/user"));
		log.info(member.getUserId());
		log.info(request.getFile("userImg").isEmpty());
		
		//유저 이미지 체크 
		if(fileService.getUserImgCount(member.getUserId())) {
			String imgPath = fileService.getUserImgPath(member.getUserId());
			boolean deleteResult = fileService.deleteUserImg(member.getUserId());
			if(deleteResult) {
				boolean deleteFile = fileService.deleteFileFromServer(imgPath);
				log.info("file is deleted from server : "+ deleteFile);
			}
		}
		
		Map<String,Object> fileInfo = fileService.getFileInfo(request, member.getUserId());
		log.info(fileInfo);
		if(fileService.insertUserImg(fileInfo)) {
			log.info("userImg is saved");
			member.setUser_img(SERVER_FILE_PATH+"/user/"+fileInfo.get("save_filename").toString());
		}else log.info("userImg is not saved");
		
		return "redirect:/mypage/mypage";
	}
	
}
