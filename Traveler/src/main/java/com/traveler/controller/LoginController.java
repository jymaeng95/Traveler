package com.traveler.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveler.api.KakaoAPI;
import com.traveler.domain.MemberVO;
import com.traveler.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class LoginController {

	@Autowired
	private KakaoAPI kakao;

	private MemberService service;
	//īī�� �α���
	@PostMapping("/index/index")
	@RequestMapping(value="/oauth")
	public String login(Model model, @RequestParam("code") String code, HttpSession session) {
		String access_Token = kakao.getAccessToken(code);
		JsonNode userInfo = kakao.getKakaoUserInfo(access_Token);
		log.info("login Controller : " + userInfo);

		String id = userInfo.get("id").toString();
		String nickname = userInfo.get("properties").get("nickname").toString();
		log.info(id);
		model.addAttribute("k_userInfo", userInfo);
		model.addAttribute("id", id);
		model.addAttribute("nickname", nickname);

		if (id != null) {
			session.setAttribute("userId", id);
			session.setAttribute("access_Token", access_Token);
		}
		return "/index/index";
	}

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		kakao.kakaoLogout((String)session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		log.info("logout");
		return "/index/index";
	}
	
	//�α��� ������ ���� �̵��� 
	@RequestMapping(value="/login/index", method=RequestMethod.GET)
	public String login() {
		log.info("login Index");
		return "/login/index";
	}


//	@RequestMapping(value="/login/login", method=RequestMethod.POST)
//	public String login(Model model,MemberVO member, RedirectAttributes rttr,HttpSession session) throws Exception {
//		log.info("login");
//		MemberVO userInfo = service.memberLogin(member);
//
//		if(userInfo != null) {
//			session.setAttribute("userId", userInfo.getUserId());
//			session.setAttribute("userPw", userInfo.getUserPw());
//			log.info("id : " + userInfo.getUserId()+ "pw : "+userInfo.getUserPw());
//			model.addAttribute("userInfo", userInfo);
//			model.addAttribute("userId", userInfo.getUserId());
//			//		model.addAttribute("nickname", nickname);
//			
//			return "redirect:/";
//		} else {
//			session.setAttribute("userId", null);
//			model.addAttribute("userId", null);
//			log.info(userInfo);
//			rttr.addFlashAttribute("result","fail");
//			return "redirect:/login/index";
//		}
//	}

	@ResponseBody
	@RequestMapping(value="/login/login",method=RequestMethod.POST)
	public boolean login(MemberVO member, HttpSession session) throws Exception {
		log.info("process login");
		MemberVO userInfo = service.memberLogin(member);
		
		if(userInfo != null) {
			session.setAttribute("userId",userInfo.getUserId());
			session.setAttribute("userPw",userInfo.getUserPw());
			return true;
		}else {
			session.setAttribute("userId",null);
			session.setAttribute("userPw",null);
			return false;
		}
	}
	
	//회원가입
	@ResponseBody
	@RequestMapping(value="/login/register", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String registerMember(MemberVO member, RedirectAttributes rttr) throws Exception {
		log.info("after Sign UP");
		log.info(member);
		boolean idCheck = service.idCheck(member);
		boolean nickCheck = service.nickCheck(member);
		log.info(idCheck+","+nickCheck);
		if(idCheck && nickCheck) {
			return "아이디/닉네임이 중복입니다.";
		}else if(idCheck) {
			return "중복된 아이디 입니다.";
		} else if(nickCheck) {
			return "중복된 닉네임 입니다.";
		}else {
			service.memberRegister(member);
			return "회원가입에 성공하였습니다.";
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/login/idCheck", method = RequestMethod.POST)
	public boolean idCheck(MemberVO member) throws Exception{
		log.info("아이디 중복 체크 ");
		return service.idCheck(member);
	}
	
	@ResponseBody
	@RequestMapping(value="/login/nickCheck", method = RequestMethod.POST)
	public boolean nickCheck(MemberVO member) throws Exception {
		log.info("닉네임 중복 체크");
		return service.nickCheck(member);
	}
	
	//�������������� ȸ������ ���� �� ���� �ʿ� 
	//@RequestMappging(value="jsp ���� ��ġ " , method=RequestMethod.GET)
	//public String modifyMemberInfo(MemberVO) throws Exception {
	//	log.info("modify Member Information");
	//	return "jsp ���� ��ġ";


	//�������������� ȸ��Ż�� �� ���� �ʿ� 
	//@RequestMappging(value="jsp ���� ��ġ " , method=RequestMethod.GET)
	//public String deleteMemberInfo(MemberVO) throws Exception {
	//	log.info("delete Member Information");
	//	return "jsp ���� ��ġ";
}
