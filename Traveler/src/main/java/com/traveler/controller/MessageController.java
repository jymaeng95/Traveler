package com.traveler.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveler.domain.MemberVO;
import com.traveler.domain.MessageVO;
import com.traveler.domain.PageVO;
import com.traveler.service.MemberService;
import com.traveler.service.MessageService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@AllArgsConstructor
public class MessageController {
	
	private MessageService service;
	
	@RequestMapping(value="/mypage/message", method=RequestMethod.GET)
	public String Message(@RequestParam(value="tabPage", defaultValue="rcv_list") String tabPage, @RequestParam(value="pageNum", defaultValue="1") String pageNo,
			@RequestParam(value="arcPage", defaultValue="rcv_arc") String arcPage, Model model,HttpSession session, MessageVO message, MemberVO member) throws Exception {	
		
		member = (MemberVO)session.getAttribute("userInfo");
		message.setUserId(member.getUserId());

		model.addAttribute("arcPage",arcPage);
		model.addAttribute("tabPage",tabPage);
		model.addAttribute("msg_list", service.getMessagePage(message));
		model.addAttribute("msg_list2", service.getMessagePage2(message));
		model.addAttribute("msg_list3", service.getMessagePage3(message));
		model.addAttribute("msg_list4", service.getMessagePage4(message));
		model.addAttribute("pageMaker", new PageVO(pageNo, service.countMessage(message),10));
		model.addAttribute("pageMaker2", new PageVO(pageNo, service.countMessage2(message),10));
		model.addAttribute("pageMaker3", new PageVO(pageNo, service.countMessage3(message),10));
		model.addAttribute("pageMaker4", new PageVO(pageNo, service.countMessage4(message),10));

		return "/mypage/message";	
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/send", method=RequestMethod.POST)
	public boolean sendMessage(MessageVO message) throws Exception {
		log.info("send message");
		log.info(message);
		return service.addMessage(message);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/delete", method=RequestMethod.POST)
	public boolean delelteMessage(MessageVO message) throws Exception {
		log.info("delete message");
		log.info(message);
		return service.deleteMessage(message);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/update", method=RequestMethod.POST)
	public boolean updateMessage(MessageVO message) throws Exception {
		log.info("update message");
		log.info(message);
		return service.updateMessage(message);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/update2", method=RequestMethod.POST)
	public boolean updateMessage2(MessageVO message) throws Exception {
		log.info("update message");
		log.info(message);
		return service.updateMessage2(message);
	}
	
	@RequestMapping(value = "/mypage/popup_more", method = RequestMethod.GET)
	public void popupGet(@RequestParam("mid") int mid, 
			@RequestParam("readType") String readType, Model model, MessageVO message) throws Exception{
		model.addAttribute("msg", service.Read(message));
		model.addAttribute("readType", readType);
	}

	@RequestMapping(value = "/mypage/popup_send", method = RequestMethod.GET)
	public void popupGet3(Model model) throws Exception{
			
	}

}
