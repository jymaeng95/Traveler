package com.traveler.mapper;

import java.util.ArrayList;

import com.traveler.domain.GroupAccVO;

public interface GroupAccMapper {
	public int insertInGroup(GroupAccVO group);
	public int countcurrent(int bno);
	public ArrayList<String> readId(int bno);
	public int isJoin(GroupAccVO group);
}
