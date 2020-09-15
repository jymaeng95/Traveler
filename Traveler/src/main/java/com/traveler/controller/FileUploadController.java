package com.traveler.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.traveler.domain.MemberVO;
import com.traveler.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class FileUploadController {
	private MemberService service;
	
	@RequestMapping(value="/upload/user/img", method = RequestMethod.POST)
	public String uploadUserImg(MultipartHttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
//		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/img/user");
		// file , request 
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info(request.getSession().getServletContext().getRealPath("/resources/upload/img/user"));
		log.info(member.getUserId());
		log.info(request.getFile("userImg").isEmpty());
		Map<String,Object> fileInfo = service.getFileInfo(request, member.getUserId());
		log.info(fileInfo);
		if(service.insertUserImg(fileInfo)) {
			log.info("userImg is saved");
		}else log.info("userImg is not saved");
		
		return "redirect:/mypage/mypage";
	}
	
	
}
