package com.traveler.service;

import java.util.List;

import com.traveler.domain.GuestVO;

public interface GuestService {
	public boolean insertGuest(GuestVO guest) throws Exception;
	public int isJoin(GuestVO guest) throws Exception;
	public List<GuestVO> readId(GuestVO guest) throws Exception;
}
