package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;
import com.traveler.domain.GroupAccVO;
import com.traveler.mapper.AccompanyMapper;
import com.traveler.mapper.GroupAccMapper;
import com.traveler.mapper.MemberMapper;
import com.traveler.mapper.MessageMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class AccompanyServiceImpl implements AccompanyService{

	private AccompanyMapper mapper;
	private GroupAccMapper g_mapper;

	@Override
	public ArrayList<AccompanyVO> readMine(AccompanyVO accompany) throws Exception {
		// TODO Auto-generated method stub
		log.info("�궡湲�遺덈윭�삤湲�");
		return mapper.readMine(accompany);
	}
	@Override
	public AccompanyVO readAcc(int bno) throws Exception {
		// TODO Auto-generated method stub
		log.info("湲� �씫湲�");
		return mapper.readAcc(bno);
	}
	@Override
	public boolean insertAcc(AccompanyVO accompany) throws Exception {
		// TODO Auto-generated method stub
		log.info("insert");
		return mapper.insertAcc(accompany) > 0;
	}
	@Override
	public boolean insertInGroup(GroupAccVO group) throws Exception {
		// TODO Auto-generated method stub
		return g_mapper.insertInGroup(group) > 0;
	}
	@Override
	public ArrayList<AccompanyVO> boardPaging(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return mapper.boardPaging(cri);
	}
	@Override
	public int countforPaging(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return mapper.countforPaging(cri);
	}
	@Override
	public int countforPaging2(String type) throws Exception {
		// TODO Auto-generated method stub
		return mapper.countforPaging2(type);
	}
	@Override
	public boolean deleteAcc(int bno) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteAcc(bno) > 0;
	}
	@Override
	public boolean updateAcc(AccompanyVO accompany) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateAcc(accompany) > 0;
	}
	@Override
	public int iswritten(int planNo) {
		// TODO Auto-generated method stub
		return mapper.iswritten(planNo);
	}

}
