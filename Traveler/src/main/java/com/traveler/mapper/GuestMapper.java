package com.traveler.mapper;

import com.traveler.domain.GuestVO;

public interface GuestMapper {
	
	public int insertGuest(GuestVO guest);
	public int isJoin(GuestVO guest);
	public String readId(GuestVO guest);
}
