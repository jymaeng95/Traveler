
package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.ReceiveMsgVO;
import com.traveler.domain.SendMsgVO;
import com.traveler.mapper.MemberMapper;
import com.traveler.mapper.MessageMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
	
	private MessageMapper mapper;
	private MemberMapper m_mapper;
	
	@Override
	public boolean addMessage(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("insert sendmsg");
	    System.out.println(sendmsg);
	    if(sendmsg.getBno_send() == null)
	    	sendmsg.setBno_send("0");
		int resultCount = mapper.messageInsert(sendmsg);
		return resultCount > 0;
	}

	@Override
	public boolean addMessage2(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("insert rcvmsg");
		if(rcvmsg.getBno_rcv()==null)
			rcvmsg.setBno_rcv("0");
		int resultCount = mapper.messageInsert2(rcvmsg);
		return resultCount > 0;
	}

	@Override
	public boolean deleteMessage(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("delete msg");
		return mapper.messageDelete(rcvmsg) > 0;
	}
	
	@Override
	public boolean deleteSendMessage(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("delete sendmsg");
		return mapper.sendmessageDelete(sendmsg) > 0;
	}


	@Override
	public boolean updateMessage(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("update msg");
		return mapper.messageUpdate(rcvmsg) > 0;
	}
	
	@Override
	public boolean updateMessage2(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("update");
		return mapper.messageUpdate2(rcvmsg) > 0;
	}
	
	@Override
	public boolean sendupdateMessage(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("update msg");
		return mapper.sendmessageUpdate(sendmsg) > 0;
	}
	
	//---�럹�씠吏�
	@Override
	public ArrayList<ReceiveMsgVO> getMessagePage(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("get rcv_list : ");
		return mapper.messagePaging(rcvmsg);
	}

	@Override
	public int countMessage(ReceiveMsgVO rcvmsg) {
		// TODO Auto-generated method stub
		return mapper.countMessage(rcvmsg);
	}
	@Override
	public int countMessage2(SendMsgVO sendmsg) {
		// TODO Auto-generated method stub
		return mapper.countMessage2(sendmsg);
	}
	@Override
	public int countMessage3(ReceiveMsgVO rcvmsg) {
		// TODO Auto-generated method stub
		return mapper.countMessage3(rcvmsg);
	}
	@Override
	public int countMessage4(SendMsgVO sendmsg) {
		// TODO Auto-generated method stub
		return mapper.countMessage4(sendmsg);
	}

	@Override
	public ArrayList<SendMsgVO> getMessagePage2(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("get send_list : ");
		return mapper.messagePaging2(sendmsg);
	}

	@Override
	public ArrayList<ReceiveMsgVO> getMessagePage3(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("get rcv_list archive : ");
		return mapper.messagePaging3(rcvmsg);
	}

	@Override
	public ArrayList<SendMsgVO> getMessagePage4(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		log.info("get send_list archive : ");
		return mapper.messagePaging4(sendmsg);
	}

	@Override
	public ReceiveMsgVO Read(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		return mapper.Read(rcvmsg);
	}
	
	@Override
	public SendMsgVO ReadSendmsg(SendMsgVO sendmsg) throws Exception {
		// TODO Auto-generated method stub
		return mapper.ReadSendmsg(sendmsg);
	}

	@Override
	public ArrayList<ReceiveMsgVO> getMessagePage5(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		return mapper.messagePaging5(rcvmsg);
	}

	@Override
	public int countMessage5(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		return mapper.countMessage5(rcvmsg);
	}

	@Override
	public int cntNoread(String userId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.cntNoread(userId);
	}

	@Override
	public int cntApply(ReceiveMsgVO rcvmsg) throws Exception {
		// TODO Auto-generated method stub
		return mapper.cntApply(rcvmsg);
	}

}
