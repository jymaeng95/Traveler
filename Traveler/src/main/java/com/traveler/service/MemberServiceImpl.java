package com.traveler.service;

import org.springframework.stereotype.Service;

import com.traveler.domain.MemberVO;
import com.traveler.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private MemberMapper mapper;
	
	@Override
	public void memberRegister(MemberVO member) {
		// TODO Auto-generated method stub
		log.info("register : "+member);
		mapper.insert(member);
	}
	
	@Override
	public void kakaoRegister(MemberVO member) {
		log.info("kakao Login : "+member);
		mapper.kakaoSignUp(member);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO member) {
		// TODO Auto-generated method stub
		log.info("Member login : " + member);
		
		return mapper.read(member);
	}

	@Override
	public boolean modifyMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		log.info("Member Modify : " +  member);
		
		return mapper.update(member) > 0 ;
	}

	@Override
	public boolean deleteMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		log.info("Member Delete : " + member );
		
		return mapper.delete(member) > 0;
	}

	@Override
	public boolean idCheck(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		log.info("Member 占쏙옙占싱듸옙 占쌩븝옙 : " + member);
		return mapper.idCheck(member) > 0;
	}

	@Override
	public boolean nickCheck(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		log.info("Member 占싻놂옙占쏙옙 占쌩븝옙 : " + member);
		return mapper.nickCheck(member) > 0;
	}
	@Override
	public boolean pwCheck(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		log.info("Member 占싻쏙옙占쏙옙占쏙옙 체크 : " + member);
		return mapper.pwCheck(member) > 0;
	}
	
	@Override
	public MemberVO loadInfo(MemberVO member) {
		// TODO Auto-generated method stub
		log.info("Member load : " + member);
		
		return mapper.loadInfo(member);
	}

	@Override
	public MemberVO kakaoLogin(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return mapper.kakaoRead(member);
	}

	@Override
	public boolean deleteKakaoMember(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteKakao(member) > 0 ;
	}
	
	@Override
	public boolean updateMemberImg(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		
		return mapper.updateMemberImg(member)>0;
	}

} 
