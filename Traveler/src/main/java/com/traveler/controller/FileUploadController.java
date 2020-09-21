package com.traveler.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.traveler.domain.MemberVO;
import com.traveler.domain.PlannerVO;
import com.traveler.service.FileUploadService;
import com.traveler.service.MemberService;
import com.traveler.service.PlannerService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class FileUploadController {
	
	private FileUploadService fileService;
	private MemberService memberService;
	private PlannerService plannerService;
	private final static String SERVER_FILE_PATH = "/resources/upload/img";
	
	@RequestMapping(value="/upload/user/img", method = RequestMethod.POST)
	public String uploadUserImg(MultipartHttpServletRequest request,HttpSession session) throws Exception {
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
		MultipartFile file = request.getFile("userImg");
		String path = request.getSession().getServletContext().getRealPath("/resources/upload/img/user");
		
		Map<String,Object> fileInfo = fileService.getFileInfo(file, path, member.getUserId());
		log.info(fileInfo);
		if(fileService.insertUserImg(fileInfo)) {
			log.info("userImg is saved");
			member.setUser_img(SERVER_FILE_PATH+"/user/"+fileInfo.get("save_filename").toString());
			if(memberService.updateMemberImg(member)) {
				log.info("userImg is updated");
			}else log.info("userImg is not updated");
		}else log.info("userImg is not saved");
		
		return "redirect:/mypage/mypage";
	}
	
	@ResponseBody
	@RequestMapping(value="/upload/plan/img", method=RequestMethod.POST)
	public PlannerVO uploadPlanImg(PlannerVO planner, MultipartHttpServletRequest request, HttpSession session) throws IllegalStateException, IOException {
		MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("detail to controller : " + planner);
		log.info("detail to controller : " + request.getFile("plannerImg").getName());
		log.info("detail to controller : " + request.getFile("plannerImg").getOriginalFilename());
		
		planner.setUserId(member.getUserId());
		if(fileService.getPlanImgCount(planner)) {
			String imgPath = fileService.getPlanImgPath(planner);
			boolean deleteResult = fileService.deletePlanImg(planner);
			if(deleteResult) {
				boolean deleteFile = fileService.deleteFileFromServer(imgPath);
				log.info("file is deleted from server : "+ deleteFile);
			}
		}
		MultipartFile file = request.getFile("plannerImg");
		String path = request.getSession().getServletContext().getRealPath("/resources/upload/img/plan");
		
		Map<String,Object> fileInfo = fileService.getFileInfo(file, path, member.getUserId());
		fileInfo.put("planNo",planner.getPlanNo());
		log.info(fileInfo);
		if(fileService.insertPlanImg(fileInfo)) {
			log.info("planImg is saved");
			planner.setPlanImg(SERVER_FILE_PATH+"/plan/"+fileInfo.get("save_filename").toString());
			if(plannerService.updatePlannerImg(planner)) {
				log.info("planner Img is updated");
			}else log.info("planner Img is not updated");
		}else log.info("userImg is not saved");
		
		return planner;
	}
}
