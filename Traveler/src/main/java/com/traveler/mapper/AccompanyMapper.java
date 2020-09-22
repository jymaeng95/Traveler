package com.traveler.mapper;

import java.util.ArrayList;

import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;

public interface AccompanyMapper {
	public ArrayList<AccompanyVO> readMine(AccompanyVO accompany);
	public AccompanyVO readAcc(int bno);
	public int insertAcc(AccompanyVO accompany);
	public int deleteAcc(int bno);
	public int updateAcc(AccompanyVO accompany);
	public ArrayList<AccompanyVO> boardPaging(Criteria cri);
	public int countforPaging(Criteria cri);
	public int countforPaging2(String type);
	public int iswritten(int planNO);
}
