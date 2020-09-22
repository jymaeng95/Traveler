package com.traveler.service;

import java.util.ArrayList;

import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;
import com.traveler.domain.GroupAccVO;

public interface AccompanyService {

	public ArrayList<AccompanyVO> readMine(AccompanyVO accompany) throws Exception;
	public AccompanyVO readAcc(int bno) throws Exception;
	public boolean insertAcc(AccompanyVO accompany) throws Exception;
	public boolean deleteAcc(int bno) throws Exception;
	public boolean updateAcc(AccompanyVO accompany) throws Exception;
	public ArrayList<AccompanyVO> boardPaging(Criteria cri) throws Exception;
	public int countforPaging(Criteria cri) throws Exception;
	public int countforPaging2(String type) throws Exception;
	public int iswritten(int planNo);
	
	public boolean insertInGroup(GroupAccVO group) throws Exception;
	
}
