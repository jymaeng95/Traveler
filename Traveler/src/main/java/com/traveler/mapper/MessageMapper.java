package com.traveler.mapper;

import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.ReceiveMsgVO;
import com.traveler.domain.SendMsgVO;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;



public interface MessageMapper {

   // Create
   public int messageInsert(SendMsgVO sendmsg);
   public int messageInsert2(ReceiveMsgVO rcvmsg);
   // Read

   public ArrayList<ReceiveMsgVO> messagePaging(ReceiveMsgVO rcvmsg);
   public ArrayList<SendMsgVO> messagePaging2(SendMsgVO sendmsg);
   public ArrayList<ReceiveMsgVO> messagePaging3(ReceiveMsgVO rcvmsg);
   public ArrayList<SendMsgVO> messagePaging4(SendMsgVO sendmsg);
   public ArrayList<ReceiveMsgVO> messagePaging5(ReceiveMsgVO rcvmsg);
   
   public ReceiveMsgVO Read(ReceiveMsgVO rcvmsg);
   public SendMsgVO ReadSendmsg(SendMsgVO sendmsg);
   
   public int countMessage(ReceiveMsgVO rcvmsg);
   public int countMessage2(SendMsgVO sendmsg);
   public int countMessage3(ReceiveMsgVO rcvmsg);
   public int countMessage4(SendMsgVO sendmsg);
   public int countMessage5(ReceiveMsgVO rcvmsg);
   public int cntNoread(String userId);
   public int cntApply(ReceiveMsgVO rcvmsg);
   // Delete
   public int messageDelete(ReceiveMsgVO rcvmsg);
   public int sendmessageDelete(SendMsgVO sendmsg);
   
   // Update
   public int messageUpdate(ReceiveMsgVO rcvmsg);
   public int messageUpdate2(ReceiveMsgVO rcvmsg);
   public int sendmessageUpdate(SendMsgVO sendmsg);

}