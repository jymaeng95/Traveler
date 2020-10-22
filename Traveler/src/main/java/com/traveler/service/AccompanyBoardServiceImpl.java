package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.Criteria;
import com.traveler.mapper.AccompanyBoardMapper;
import com.traveler.mapper.AccompanyMapper;
import com.traveler.mapper.GroupAccMapper;

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
   
}