package com.traveler.domain;

import lombok.Data;

@Data
public class PlannerVO {
	private int planNo;
	private String userId;
	private String planTitle;
	private String info;
	private String planImg;
	private MemberVO memberVO;
}
