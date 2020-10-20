package com.traveler.service;

import org.springframework.stereotype.Service;

import com.traveler.domain.GuestVO;
import com.traveler.mapper.GuestMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
	
	private GuestMapper mapper;
	
	@Override
	public boolean insertGuest(GuestVO guest) throws Exception {
		// TODO Auto-generated method stub
		String str = guest.getTitle().toString();
		String title = str.substring(str.indexOf("(")+1,str.indexOf(")"));
		guest.setTitle(title);
		
		return mapper.insertGuest(guest) > 0;
	}

	@Override
	public int isJoin(GuestVO guest) throws Exception {
		// TODO Auto-generated method stub
		return mapper.isJoin(guest);
	}

	@Override
	public String readId(GuestVO guest) throws Exception {
		// TODO Auto-generated method stub
		return mapper.readId(guest);
	}

}