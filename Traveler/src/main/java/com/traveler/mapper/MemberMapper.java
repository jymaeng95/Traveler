package com.traveler.mapper;

import com.traveler.domain.MemberVO;

public interface MemberMapper {
	// Member CRUD ���� 
	// Create
	public void insert(MemberVO member);
	
	// Read
	public MemberVO read(MemberVO member);

	// Update
	public int update(MemberVO member);
	
	// Delete
	public int delete(MemberVO member);
	
	//���̵� �ߺ� üũ 
	public int idCheck(MemberVO member);

	//�г��� �ߺ� üũ 
	public int nickCheck(MemberVO member);
	
	public int pwCheck(MemberVO member);

	public MemberVO loadInfo(MemberVO member);
	
	public void kakaoSignUp(MemberVO member);
	
	public MemberVO kakaoRead(MemberVO member);
}
