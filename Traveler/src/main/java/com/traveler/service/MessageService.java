package com.traveler.service;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.ReceiveMsgVO;
import com.traveler.domain.SendMsgVO;

public interface MessageService {
	
	public boolean addMessage(SendMsgVO sendmsg) throws Exception;
	
	public boolean addMessage2(ReceiveMsgVO rcvmsg) throws Exception;
	
	public ArrayList<ReceiveMsgVO> getMessagePage(ReceiveMsgVO rcvmsg) throws Exception;
	
	public ArrayList<SendMsgVO> getMessagePage2(SendMsgVO sendmsg) throws Exception;
	
	public ArrayList<ReceiveMsgVO> getMessagePage3(ReceiveMsgVO rcvmsg) throws Exception;
	
	public ArrayList<SendMsgVO> getMessagePage4(SendMsgVO sendmsg) throws Exception;
	
	public ArrayList<ReceiveMsgVO> getMessagePage5(ReceiveMsgVO rcvmsg) throws Exception;
	
	public ReceiveMsgVO Read(ReceiveMsgVO rcvmsg) throws Exception;
	
	public SendMsgVO ReadSendmsg(SendMsgVO sendmsg) throws Exception;
	
	public int countMessage(ReceiveMsgVO rcvmsg) throws Exception;
	public int countMessage2(SendMsgVO sendmsg) throws Exception;
	public int countMessage3(ReceiveMsgVO rcvmsg) throws Exception;
	public int countMessage4(SendMsgVO sendmsg) throws Exception;
	public int countMessage5(ReceiveMsgVO rcvmsg) throws Exception;
	public int cntNoread(String userId) throws Exception;
	public int cntApply(ReceiveMsgVO rcvmsg) throws Exception;
	
	public boolean deleteMessage(ReceiveMsgVO rcvmsg) throws Exception;
	public boolean deleteSendMessage(SendMsgVO sendmsg) throws Exception;
	
	public boolean updateMessage(ReceiveMsgVO rcvmsg) throws Exception;
	public boolean updateMessage2(ReceiveMsgVO rcvmsg) throws Exception;
	public boolean sendupdateMessage(SendMsgVO sendmsg) throws Exception;

}
