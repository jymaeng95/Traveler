package com.traveler.domain;

import lombok.Data;
import java.sql.Date;

@Data	
public class SendMsgVO {
	
	private int mid_send;
	private String targetId_send;
	private String mcontent_send;
	private String sender_send;
	private String senddate_send;
	private String readstatus_send;	
	private String status_send;
	private String bno_send;
	private MemberVO memberVO;
	private int pageNum = 1;
	private String tabPage = "rcv_list";
	private String arcPage = "rcv_arc";
	private String readType = "rcv";
	private PageVO pageVO;
	private ReceiveMsgVO ReceiveMsgVO;
}