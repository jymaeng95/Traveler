package com.traveler.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.traveler.domain.MemberVO;

public interface MemberService {
	
	//회원가입 
	public void memberRegister(MemberVO member) throws Exception;
	
	//카카오 회원가입
	public void kakaoRegister(MemberVO member) throws Exception;
	
	//카카오 로그인
	public MemberVO kakaoLogin(MemberVO member) throws Exception;
	
	//회원 로그인
	public MemberVO memberLogin(MemberVO member) throws Exception;
	
	//회원수정
	public boolean modifyMember(MemberVO member) throws Exception;
	
	//회원탈퇴 
	public boolean deleteMember(MemberVO member) throws Exception;
	
	//카카오 회원탈퇴 
	public boolean deleteKakaoMember(MemberVO member) throws Exception;
	
	//아이디 중복 체크 
	public boolean idCheck(MemberVO member) throws Exception;
	
	//닉네임 중복 체크 
	public boolean nickCheck(MemberVO member) throws Exception;

	//패스워드 체크 
	public boolean pwCheck(MemberVO member) throws Exception;

	public MemberVO loadInfo(MemberVO member) throws Exception;
	
	public boolean updateMemberImg(MemberVO member) throws Exception;
}
