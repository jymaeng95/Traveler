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
import com.traveler.service.FileUploadService;
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
	public String login(Model model, @RequestParam("code") String code, HttpSession session) throws Exception {
		String access_Token = kakao.getAccessToken(code);
		JsonNode userInfo = kakao.getKakaoUserInfo(access_Token);
		log.info("login Controller : " + userInfo);

		MemberVO k_userInfo = new MemberVO();
		k_userInfo.setUserId(userInfo.get("id").toString());
		k_userInfo.setNickname(userInfo.get("properties").get("nickname").toString().replaceAll("\"", ""));

		if(userInfo.get("kakao_account").get("profile").get("thumbnail_image_url") == null) {
			k_userInfo.setUser_img("");
		} else {
			k_userInfo.setUser_img(userInfo.get("kakao_account").get("profile").get("thumbnail_image_url").toString().replaceAll("\"", ""));
		}
		MemberVO member = service.kakaoLogin(k_userInfo);
		log.info("member for kakao login : " + member);
		//移댁뭅�삤濡� �븳踰덉씠�씪�룄 濡쒓렇�씤 �뻽�쑝硫� �븞�븯寃� �븿
		//泥섏쓬 移댁뭅�삤 濡쒓렇�씤�씠�씪硫� �쉶�썝媛��엯 �뱾�뼱媛�寃뚰빐�빞�븿
		if(member == null) {
			service.kakaoRegister(k_userInfo);
			member = service.kakaoLogin(k_userInfo);
			log.info("member in register for kakao : "+member);
		}
		
		if(!member.getUser_img().equals(k_userInfo.getUser_img())) {
			member.setUser_img(k_userInfo.getUser_img());
			if(service.updateMemberImg(member)) log.info("kakao account latest img is updated");
			else log.info("kakao account latest img is not updated");
				
		}
		model.addAttribute("k_userInfo", member);
		model.addAttribute("id", member.getUserId());
		log.info("카카오 최신 이미지"+k_userInfo.getUser_img());
		log.info("member 이미지"+member.getUser_img());
		
		if (k_userInfo.getUserId() != null) {
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("userInfo", member);
		}
		return "/index/index";
	}

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		kakao.kakaoLogout((String)session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userInfo");
		session.invalidate();
		log.info("logout");
		return "/index/index";
	}

	//�α��� ������ ���� �̵��� 
	@RequestMapping(value="/login/index", method=RequestMethod.GET)
	public String login() {
		log.info("login Index");
		return "/login/index";
	}

	@ResponseBody
	@RequestMapping(value="/login/login",method=RequestMethod.POST)
	public boolean login(MemberVO member, HttpSession session) throws Exception {
		log.info("process login");
		MemberVO userInfo = service.memberLogin(member);
		
		if(userInfo != null) {
	
			session.setAttribute("userInfo", userInfo);
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
}
