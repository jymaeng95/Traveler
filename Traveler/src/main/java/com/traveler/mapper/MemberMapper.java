package com.traveler.mapper;

import java.util.Map;

import com.traveler.domain.MemberVO;

public interface MemberMapper {
	// Member CRUD 占쏙옙占쏙옙 
	// Create
	public void insert(MemberVO member);
	
	// Read
	public MemberVO read(MemberVO member);

	// Update
	public int update(MemberVO member);
	
	// Delete
	public int delete(MemberVO member);
	
	//占쏙옙占싱듸옙 占쌩븝옙 체크 
	public int idCheck(MemberVO member);

	//占싻놂옙占쏙옙 占쌩븝옙 체크 
	public int nickCheck(MemberVO member);
	
	public int pwCheck(MemberVO member);

	public MemberVO loadInfo(MemberVO member);
	
	public void kakaoSignUp(MemberVO member);
	
	public int deleteKakao(MemberVO member);
	
	public MemberVO kakaoRead(MemberVO member);
	
	public int updateMemberImg(MemberVO member);
}
