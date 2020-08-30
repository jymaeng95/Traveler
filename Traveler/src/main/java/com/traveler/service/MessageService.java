package com.traveler.service;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

import com.traveler.domain.MemberVO;
import com.traveler.domain.MessageVO;
import com.traveler.domain.PageVO;

public interface MessageService {
	
	public boolean addMessage(MessageVO message) throws Exception;
	
	public ArrayList<MessageVO> getMessagePage(MessageVO message) throws Exception;
	
	public ArrayList<MessageVO> getMessagePage2(MessageVO message) throws Exception;
	
	public ArrayList<MessageVO> getMessagePage3(MessageVO message) throws Exception;
	
	public ArrayList<MessageVO> getMessagePage4(MessageVO message) throws Exception;
	
	public MessageVO Read(MessageVO message) throws Exception;
	
	public int countMessage(MessageVO message) throws Exception;
	public int countMessage2(MessageVO message) throws Exception;
	public int countMessage3(MessageVO message) throws Exception;
	public int countMessage4(MessageVO message) throws Exception;
	
	
	public boolean deleteMessage(MessageVO message) throws Exception;
	
	public boolean updateMessage(MessageVO message) throws Exception;
	public boolean updateMessage2(MessageVO message) throws Exception;
}
