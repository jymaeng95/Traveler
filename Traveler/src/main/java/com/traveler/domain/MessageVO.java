package com.traveler.domain;

import lombok.Data;
import java.sql.Date;

@Data	
public class MessageVO {
	
	private int mid;
	private String sender;
	private String mcontent;
	private String userId;
	private String senddate;
	private String readstatus;	
	private String status;
	private MemberVO memberVO;
	private int pageNum = 1;
	private String tabPage = "rcv_list";
	private String arcPage = "rcv_arc";
	private PageVO pageVO;
}