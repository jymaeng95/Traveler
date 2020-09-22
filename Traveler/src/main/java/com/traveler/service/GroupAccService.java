package com.traveler.service;

import java.util.ArrayList;

import com.traveler.domain.GroupAccVO;

public interface GroupAccService {
	public int countnum(int bno) throws Exception;
	public ArrayList<String> readId(int bno) throws Exception;
	public int isJoin(GroupAccVO group) throws Exception;
}
