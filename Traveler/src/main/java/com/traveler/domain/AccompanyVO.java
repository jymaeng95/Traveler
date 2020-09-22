package com.traveler.domain;

import lombok.Data;

@Data	
public class AccompanyVO {
	private int bno;
	private int planNo;
	private int numperson;
	private int curperson;
	private String userId;
	private String title;
	private String planDate;
	private String planTotalDate;
	private String content;
	private String writeDate;
	private MemberVO MemberVO;
	private int pageNum = 1;
	private String boardtype = "main";
}