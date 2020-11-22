package com.traveler.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.ReceiveMsgVO;
import com.traveler.domain.SendMsgVO;
import com.traveler.service.MemberService;
import com.traveler.service.MessageService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@AllArgsConstructor
public class MessageController {
	
	private MessageService service;
	private MemberService m_service;
	
	@RequestMapping(value="/mypage/message", method=RequestMethod.GET)
	public String Message(@RequestParam(value="tabPage", defaultValue="rcv_list") String tabPage, @RequestParam(value="pageNum", defaultValue="1") String pageNo,
			@RequestParam(value="arcPage", defaultValue="rcv_arc") String arcPage, Model model,HttpSession session, ReceiveMsgVO rcvmsg, SendMsgVO sendmsg) throws Exception {	
		
		MemberVO member = (MemberVO)session.getAttribute("userInfo");
		sendmsg.setTargetId_send(member.getUserId());
		rcvmsg.setTargetId_rcv(member.getUserId());

		model.addAttribute("arcPage",arcPage);
		model.addAttribute("tabPage",tabPage);
		model.addAttribute("msg_list", service.getMessagePage(rcvmsg));
		model.addAttribute("msg_list2", service.getMessagePage2(sendmsg));
		model.addAttribute("msg_list3", service.getMessagePage3(rcvmsg));
		model.addAttribute("msg_list4", service.getMessagePage4(sendmsg));
		model.addAttribute("msg_list5", service.getMessagePage5(rcvmsg));
		model.addAttribute("pageMaker", new PageVO(pageNo, service.countMessage(rcvmsg),10));
		model.addAttribute("pageMaker2", new PageVO(pageNo, service.countMessage2(sendmsg),10));
		model.addAttribute("pageMaker3", new PageVO(pageNo, service.countMessage3(rcvmsg),10));
		model.addAttribute("pageMaker4", new PageVO(pageNo, service.countMessage4(sendmsg),10));
		model.addAttribute("pageMaker5", new PageVO(pageNo, service.countMessage5(rcvmsg),10));

		return "/mypage/message";	
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/send", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String sendMessage(SendMsgVO sendmsg, ReceiveMsgVO rcvmsg, MemberVO member) throws Exception {
		log.info("send message");
		log.info(m_service.idCheck(member));
		log.info(sendmsg);
		log.info(rcvmsg);
		boolean idCheck = m_service.idCheck(member);
		
		if(!idCheck) return "존재하지 않는 아이디입니다.";
		else {
			service.addMessage(sendmsg);
			service.addMessage2(rcvmsg);
			return "right";
		}
 
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/delete", method=RequestMethod.POST)
	public boolean delelteMessage(ReceiveMsgVO rcvmsg, SendMsgVO sendmsg, HttpServletRequest request) throws Exception {
		log.info("delete message");
		
		String type = request.getParameter("type").toString();
		String s1 = "send";
		String s2 = "send_arc";
		if(type.equals(s1) || type.equals(s2)) return service.deleteSendMessage(sendmsg);
		else return service.deleteMessage(rcvmsg);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/update", method=RequestMethod.POST)
	public boolean updateMessage(ReceiveMsgVO rcvmsg, SendMsgVO sendmsg, HttpServletRequest request) throws Exception {
		log.info("update message ");
		String type = request.getParameter("type").toString();
		String s = "send";
		if(type.equals(s)) return service.sendupdateMessage(sendmsg);
		else return service.updateMessage(rcvmsg);
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/update2", method=RequestMethod.POST)
	public boolean updateMessage2(ReceiveMsgVO rcvmsg) throws Exception {
		log.info("update message");
		return service.updateMessage2(rcvmsg);
	}
	
	@RequestMapping(value = "/mypage/popup_more", method = RequestMethod.GET)
	public void popupGet(@RequestParam("mid") int mid, 
			@RequestParam("readType") String readType, Model model, ReceiveMsgVO rcvmsg, SendMsgVO sendmsg, HttpSession session) throws Exception{
		
		MemberVO member = (MemberVO)session.getAttribute("userInfo");
		rcvmsg.setTargetId_rcv(member.getUserId());
		rcvmsg.setMid_rcv(mid);
		sendmsg.setSender_send(member.getUserId());
		sendmsg.setMid_send(mid);
		
		if(readType.equals("send")) {
			System.out.println("send");
			model.addAttribute("msg", service.ReadSendmsg(sendmsg));
			model.addAttribute("readType", readType);		
		}
		else {
			System.out.println("rcv/accom");
			model.addAttribute("msg", service.Read(rcvmsg));
			model.addAttribute("readType", readType);
		}
		}

	@RequestMapping(value = "/mypage/popup_send", method = RequestMethod.GET)
	public void popupGet3(@RequestParam("rcver") String rcver, Model model) throws Exception{
		model.addAttribute("rcver", rcver);	
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/cntnoread", method=RequestMethod.POST)
	public int cntNoRead(String userId) throws Exception {
		int result = service.cntNoread(userId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/mypage/message/cntapply", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String cntApply(ReceiveMsgVO rcvmsg) throws Exception {
		int result = service.cntApply(rcvmsg);
		if(result>0) return "이미 신청했습니다.";
		return "";
	}
}
