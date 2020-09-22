package com.traveler.domain;

import lombok.Data;

@Data
public class GroupAccVO {
	private int bno;
	private String leader;
	private String userId;
	private AccompanyVO AccompanyVO;
}
