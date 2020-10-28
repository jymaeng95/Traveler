package com.traveler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.Criteria;
import com.traveler.mapper.AccompanyBoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class AccompanyBoardServiceImpl implements AccompanyBoardService{

   private AccompanyBoardMapper mapper;

   @Override
   public ArrayList<AccompanyBoardVO> readBoard(Criteria cri) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readBoard(cri);
   }

   @Override
   public AccompanyBoardVO readAcc(int acc_bno) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readAcc(acc_bno);
   }

   @Override
   public boolean insertAcc(AccompanyBoardVO accompany) throws Exception {
      // TODO Auto-generated method stub
      return mapper.insertAcc(accompany)>0;
   }

   @Override
   public int cntforpaging() throws Exception {
      // TODO Auto-generated method stub
      return mapper.cntforpaging();
   }

   @Override
   public boolean updateAcc(AccompanyBoardVO accompany) throws Exception {
      // TODO Auto-generated method stub
      return mapper.updateAcc(accompany)>0;
   }
   @Override
   public List<Map<String,Object>> getRecommendAccompany(String planDate, String startDate, String title) throws Exception {
      // TODO Auto-generated method stub
      Map<String,Object> acc_data = new HashMap<>();
      acc_data.put("planDate",planDate);
      acc_data.put("startDate",startDate);
      acc_data.put("title",title);
      return mapper.readRecommendAccompany(acc_data);
   }

   public int readAccBno(AccompanyBoardVO accompany) throws Exception {
      return mapper.readAccBno(accompany);
   }

   public boolean deleteAcc(AccompanyBoardVO accompany) throws Exception {
      // TODO Auto-generated method stub
      return mapper.deleteAcc(accompany)>0;
   }
   
   @Override
   public List<Integer> readbno(String pageNum) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readbno(pageNum);
   }

   @Override
   public ArrayList<AccompanyBoardVO> readmyAcc(String hostId) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readmyAcc(hostId);
   }
   
   @Override
   public AccompanyBoardVO readguestAcc(int accbno) throws Exception {
      // TODO Auto-generated method stub
      return mapper.readguestAcc(accbno);
   }
}