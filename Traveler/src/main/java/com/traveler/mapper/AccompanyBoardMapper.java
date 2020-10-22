package com.traveler.mapper;

import java.util.ArrayList;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;

public interface AccompanyBoardMapper {
   
   public ArrayList<AccompanyBoardVO> readBoard(Criteria cri);
   public AccompanyBoardVO readAcc(int acc_bno);
   public int insertAcc(AccompanyBoardVO accompany);
   public int updateAcc(AccompanyBoardVO accompany);
   public int cntforpaging();
}