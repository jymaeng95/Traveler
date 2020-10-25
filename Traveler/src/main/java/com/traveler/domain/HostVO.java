package com.traveler.domain;

import lombok.Data;

@Data
public class HostVO {
	private int accBno;
	private int planNo;
	private String hostId;
	private String title;
	private int limitPerson;
	private int curPerson;
	private String descript;
}
