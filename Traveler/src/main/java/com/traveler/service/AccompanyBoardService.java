package com.traveler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.Criteria;

public interface AccompanyBoardService {

   public ArrayList<AccompanyBoardVO> readBoard(Criteria cri) throws Exception;
   public AccompanyBoardVO readAcc(int acc_bno) throws Exception;
   public boolean insertAcc(AccompanyBoardVO accompany) throws Exception;
   public boolean updateAcc(AccompanyBoardVO accompany) throws Exception;
   public int cntforpaging() throws Exception;
   public List<Map<String,Object>> getRecommendAccompany(String planDate, String startDate, String title) throws Exception;
   public int readAccBno(AccompanyBoardVO accompany) throws Exception;
   public boolean deleteAcc(AccompanyBoardVO accompany) throws Exception;
   public List<Integer> readbno(String pageNum) throws Exception;
   public ArrayList<AccompanyBoardVO> readmyAcc(String hostId) throws Exception;
   public AccompanyBoardVO readguestAcc(int accbno) throws Exception;
}