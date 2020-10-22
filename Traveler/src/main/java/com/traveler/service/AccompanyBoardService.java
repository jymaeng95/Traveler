package com.traveler.service;

import java.util.ArrayList;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;

public interface AccompanyBoardService {
   
   public ArrayList<AccompanyBoardVO> readBoard(Criteria cri) throws Exception;
   public AccompanyBoardVO readAcc(int acc_bno) throws Exception;
   public boolean insertAcc(AccompanyBoardVO accompany) throws Exception;
   public boolean updateAcc(AccompanyBoardVO accompany) throws Exception;
   public int cntforpaging() throws Exception;
}