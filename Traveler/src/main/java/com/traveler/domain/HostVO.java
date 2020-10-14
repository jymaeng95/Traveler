package com.traveler.domain;

import lombok.Data;

@Data
public class HostVO {
	private int planNo;
	private String hostid;
	private String title;
	private int limitPerson;
	private int curPerson;
	private String descript;
	private String regDate;
}
