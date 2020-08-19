package com.traveler.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.traveler.domain.MemberVO;
import com.traveler.domain.MessageVO;
import com.traveler.domain.PageVO;
import com.traveler.mapper.MessageMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
	
	private MessageMapper mapper;
	
	@Override
	public boolean addMessage(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("메세지 저장  " + message);
		int resultCount = mapper.messageInsert(message);
		return resultCount > 0;
	}

//	@Override
//	public ArrayList<MessageVO> getUserMessage(MessageVO message) throws Exception {
//		// TODO Auto-generated method stub
//		log.info("받은 메세지 불러오기  :" + message);
//		return mapper.messageRead(message);
//	}

	@Override
	public boolean deleteMessage(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("메세지 삭제  :" + message);
		return mapper.messageDelete(message) > 0;
	}

	@Override
	public boolean updateMessage(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("보관함 저장: " + message);
		return mapper.messageUpdate(message) > 0;
	}
	
	@Override
	public boolean updateMessage2(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("읽음처리: " + message);
		return mapper.messageUpdate2(message) > 0;
	}

//	@Override
//	public ArrayList<MessageVO> getUserMessage2(MessageVO message) throws Exception {
//		// TODO Auto-generated method stub
//		log.info("보낸 메세지 불러오기  :" + message);
//		return mapper.messageRead2(message);
//	}

	
	//---페이징
	@Override
	public ArrayList<MessageVO> getMessagePage(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("get rcv_list : " + message);
		return mapper.messagePaging(message);
	}

	@Override
	public int countMessage(MessageVO message) {
		// TODO Auto-generated method stub
		return mapper.countMessage(message);
	}
	@Override
	public int countMessage2(MessageVO message) {
		// TODO Auto-generated method stub
		return mapper.countMessage2(message);
	}
	@Override
	public int countMessage3(MessageVO message) {
		// TODO Auto-generated method stub
		return mapper.countMessage3(message);
	}
	@Override
	public int countMessage4(MessageVO message) {
		// TODO Auto-generated method stub
		return mapper.countMessage4(message);
	}

	@Override
	public ArrayList<MessageVO> getMessagePage2(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("get send_list : " + message);
		return mapper.messagePaging2(message);
	}

	@Override
	public ArrayList<MessageVO> getMessagePage3(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("get rcv_list archive : " + message);
		return mapper.messagePaging3(message);
	}

	@Override
	public ArrayList<MessageVO> getMessagePage4(MessageVO message) throws Exception {
		// TODO Auto-generated method stub
		log.info("get send_list archive : " + message);
		return mapper.messagePaging4(message);
	}

}
