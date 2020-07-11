package com.traveler.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.traveler.api.KakaoAPI;
import com.traveler.domain.BookmarkVO;
import com.traveler.domain.MemberVO;
import com.traveler.service.BookmarkService;
import com.traveler.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class MyPageController {
	private MemberService service;
	private BookmarkService b_service;
	@Autowired
	private KakaoAPI kakao;
	
	@RequestMapping(value="/mypage/mypage", method=RequestMethod.GET)
	public String myPage(Model model,HttpSession session, BookmarkVO bookmark,MemberVO member) throws Exception {	
		
		bookmark.setUserId((String)session.getAttribute("userId"));
		member.setUserId((String)session.getAttribute("userId"));
		log.info(bookmark);
		
		ArrayList<BookmarkVO> bmk_list = b_service.getUserBookmark(bookmark);
		MemberVO userInfo = service.loadInfo(member);
		
		model.addAttribute("b_list",bmk_list);
		model.addAttribute("userId",session.getAttribute("userId"));
		model.addAttribute("userImg",userInfo.getUser_img());
		log.info("mypage");
		return "/mypage/mypage";
	}
	
	@RequestMapping(value="/mypage/message", method=RequestMethod.GET)
	public String userMessagePage(Model model) {
		log.info("message");
		return "/mypage/message";
	}
	@RequestMapping(value="/mypage/modify", method=RequestMethod.GET)
	public String modify(Model model,HttpSession session) {
		model.addAttribute("userId",session.getAttribute("userId"));
		log.info("modify");
		return "/mypage/modify";
	}
	
	@RequestMapping(value="/mypage/modify", method=RequestMethod.POST)
	public String modifyInfo(MemberVO member, HttpSession session) throws Exception {
		log.info("after Modify");
		log.info(member);
		service.modifyMember(member);
		kakao.kakaoLogout((String)session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		log.info("logout");
		
		return "/index/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/delete", method=RequestMethod.POST)
	public boolean deleteInfo(MemberVO member, HttpSession session) throws Exception {
		
		log.info("after Delete");
		log.info(member);
		boolean pwCheck = service.pwCheck(member);
		boolean result=false;
		
		if(pwCheck) result = service.deleteMember(member);
		else return pwCheck;
		
		if(result) session.invalidate();
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/pwdcheck", method=RequestMethod.POST)
	public boolean checkPw(MemberVO member, RedirectAttributes rttr) throws Exception {
		boolean pwCheck = service.pwCheck(member);
		log.info("패스워드 체크 :"+ pwCheck);
		return pwCheck;
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/info", method = RequestMethod.POST)
	public MemberVO loadInfo(MemberVO member) throws Exception {
		log.info("loadinfo");

		return service.loadInfo(member);
	}
}
