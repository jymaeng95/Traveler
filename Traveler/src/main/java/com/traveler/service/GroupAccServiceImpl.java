package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.traveler.domain.GroupAccVO;
import com.traveler.mapper.GroupAccMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class GroupAccServiceImpl implements GroupAccService{

	private GroupAccMapper g_mapper;

	@Override
	public int countnum(int bno) throws Exception {
		// TODO Auto-generated method stub
		return g_mapper.countcurrent(bno);
	}

	@Override
	public ArrayList<String> readId(int bno) throws Exception {
		// TODO Auto-generated method stub
		return g_mapper.readId(bno);
	}

	@Override
	public int isJoin(GroupAccVO group) throws Exception {
		// TODO Auto-generated method stub
		return g_mapper.isJoin(group);
	}

}
