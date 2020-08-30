package com.traveler.mapper;

import com.traveler.domain.MemberVO;
import com.traveler.domain.MessageVO;
import com.traveler.domain.PageVO;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;



public interface MessageMapper {

	// Create
	public int messageInsert(MessageVO message);
	
	// Read

	public ArrayList<MessageVO> messagePaging(MessageVO message);
	public ArrayList<MessageVO> messagePaging2(MessageVO message);
	public ArrayList<MessageVO> messagePaging3(MessageVO message);
	public ArrayList<MessageVO> messagePaging4(MessageVO message);
	public MessageVO Read(MessageVO message);
	
	public int countMessage(MessageVO message);
	public int countMessage2(MessageVO message);
	public int countMessage3(MessageVO message);
	public int countMessage4(MessageVO message);
	
	// Delete
	public int messageDelete(MessageVO message);
	
	// Update
	public int messageUpdate(MessageVO message);
	public int messageUpdate2(MessageVO message);
	
}