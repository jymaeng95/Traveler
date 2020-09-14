package com.traveler.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class FileUploadController {
	
	@RequestMapping(value="/upload/user/img", method = RequestMethod.POST)
	public String uploadUserImg(MultipartFile file, HttpServletRequest request) {
		String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/img/user");
		log.info("Save Dir : "+saveDir);
		log.info("file name: "+file.getName());
		log.info("file org name : "+file.getOriginalFilename());
		log.info("file size : "+file.getSize());
		log.info("flee type : "+file.getContentType());
		return "redirect:/mypage/mypage";
	}
	
}
