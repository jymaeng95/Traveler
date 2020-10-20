package com.traveler.service;

import org.springframework.stereotype.Service;

import com.traveler.domain.AccompanyBoardVO;
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
	public AccompanyBoardVO readBoard(int acc_bno) throws Exception {
		// TODO Auto-generated method stub
		return mapper.readBoard(acc_bno);
	}
	
}
