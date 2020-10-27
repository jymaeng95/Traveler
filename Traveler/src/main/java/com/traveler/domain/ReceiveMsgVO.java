package com.traveler.domain;

import lombok.Data;

@Data	
public class ReceiveMsgVO {
	
	private int mid_rcv;
	private String targetId_rcv;
	private String mcontent_rcv;
	private String sender_rcv;
	private String senddate_rcv;
	private String readstatus_rcv;	
	private String status_rcv;
	private String bno_rcv;
	private MemberVO memberVO;
	private int pageNum = 1;
	private String tabPage = "rcv_list";
	private String arcPage = "rcv_arc";
	private String readType = "rcv";
	private PageVO pageVO;
}