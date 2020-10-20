package com.traveler.service;

import com.traveler.domain.GuestVO;

public interface GuestService {
	public boolean insertGuest(GuestVO guest) throws Exception;
	public int isJoin(GuestVO guest) throws Exception;
	public String readId(GuestVO guest) throws Exception;
}
