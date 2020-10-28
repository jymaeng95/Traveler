package com.traveler.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.Criteria;

public interface AccompanyBoardMapper {

   public ArrayList<AccompanyBoardVO> readBoard(Criteria cri);
   public AccompanyBoardVO readAcc(int acc_bno);
   public int insertAcc(AccompanyBoardVO accompany);
   public int updateAcc(AccompanyBoardVO accompany);
   public int cntforpaging();
   public List<Map<String,Object>> readRecommendAccompany(Map<String,Object> acc_data);
   public int readAccBno(AccompanyBoardVO accompany);
   public int deleteAcc(AccompanyBoardVO accompany);
   public List<Integer> readbno(String pageNum);
   public ArrayList<AccompanyBoardVO> readmyAcc(String hostId);
   public AccompanyBoardVO readguestAcc(int accbno);
}