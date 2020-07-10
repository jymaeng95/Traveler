package com.traveler.service;

import com.traveler.domain.MemberVO;

public interface MemberService {
	
	//ȸ������
	public void memberRegister(MemberVO member) throws Exception;
	
	//�α���(�Ϲ�����)
	public MemberVO memberLogin(MemberVO member) throws Exception;
	
	//ȸ�� ���� ���� 
	public boolean modifyMember(MemberVO member) throws Exception;
	
	//ȸ�� Ż�� 
	public boolean deleteMember(MemberVO member) throws Exception;
	
	//���̵� �ߺ� üũ 
	public boolean idCheck(MemberVO member) throws Exception;
	
	//�г��� �ߺ� üũ 
	public boolean nickCheck(MemberVO member) throws Exception;

	public boolean pwCheck(MemberVO member) throws Exception;

	public MemberVO loadInfo(MemberVO member) throws Exception;

}
