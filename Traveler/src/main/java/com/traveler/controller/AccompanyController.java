package com.traveler.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;
import com.traveler.domain.GroupAccVO;
import com.traveler.domain.HostVO;
import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.PlannerVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.AccompanyService;
import com.traveler.service.GroupAccService;
import com.traveler.service.PlannerService;
import com.traveler.service.UserPlanService;
import com.traveler.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class AccompanyController {
	private AccompanyService service;
	private PlannerService p_service;
	private UserPlanService u_service;
	private GroupAccService g_service;

	@RequestMapping(value="/accompany/index", method=RequestMethod.GET)
	public String AccompanyIndex(Model model,HttpSession session, AccompanyVO accompany) throws Exception {	
		MemberVO member = (MemberVO)session.getAttribute("userInfo");
		List<Integer> planNo = u_service.getPlanNoAfterToday(member.getUserId());
		List<PlannerVO> planner = new ArrayList<>();
		if(planNo.size() >0) {
			for(int no : planNo) {
				planner.add(p_service.getAllPlannerFromPlanNo(no));
			}
		}
		model.addAttribute("accPlanner",planner);

		return "/accompany/index";	
	}

	@RequestMapping(value="/accompany/register", method=RequestMethod.GET)
	public String accompanyRegister(Model model, HttpSession session,@RequestParam(value="planNo") int planNo) {

		model.addAttribute("planNo",planNo);
		List<UserPlanVO> accPlan = u_service.getPlanForAccompany(planNo);
		List<String> time = new ArrayList<>();
		List<String> date = new ArrayList<>();
		for(UserPlanVO plan : accPlan) {
			if(plan.getStartDate() != null || plan.getEndDate() != null) 
				time.add(DateUtils.splitTime(plan.getStartDate() + " ~ " + DateUtils.splitTime(plan.getEndDate())));
			date.add(DateUtils.splitDate(plan.getPlanDate()));
		}
		model.addAttribute("accPlan",accPlan);
		log.info(time);
		log.info(date);
		model.addAttribute("time",time);
		model.addAttribute("date",date);
		return "/accompany/register";
	}

	@RequestMapping(value="/accompany/register/detail", method=RequestMethod.GET)
	public String guideSpot(HostVO hostVO, Model model) throws Exception {
		log.info("guide");
		model.addAttribute("planNo", hostVO.getPlanNo());
		model.addAttribute("title", hostVO.getTitle());
		return "/accompany/register-modal";
	}
		
	@RequestMapping(value="/accompany/board", method=RequestMethod.GET)
	public String Accompany(@RequestParam(value="pageNum", defaultValue="1") String pageNo,
			Model model,HttpSession session, AccompanyVO accompany, Criteria cri) throws Exception {	

		MemberVO member = (MemberVO)session.getAttribute("userInfo");
		accompany.setUserId(member.getUserId());

		model.addAttribute("board", service.boardPaging(cri));
		model.addAttribute("pageMaker", new PageVO(cri, service.countforPaging(cri)));
		model.addAttribute("count", service.countforPaging(cri));

		return "/accompany/board";	
	}


	@RequestMapping(value="/accompany/write", method=RequestMethod.GET)
	public String AccompanyWrite(@RequestParam(value="planNo") Integer planNo, Model model,HttpSession session, AccompanyVO accompany, MemberVO member, PlannerVO planner, UserPlanVO userplan) throws Exception {	
		member = (MemberVO)session.getAttribute("userInfo");
		accompany.setUserId(member.getUserId());
		planner.setUserId(member.getUserId());
		userplan.setUserId(member.getUserId());

		model.addAttribute("planNo", planNo);
		model.addAttribute("startDate", u_service.getStartDate(userplan));
		model.addAttribute("u_list", u_service.getUserPlanFromPlanNo(planNo, member.getUserId()));
		return "/accompany/write";	
	}

	@RequestMapping(value="/accompany/modify", method=RequestMethod.GET)
	public String AccompanyModify(@RequestParam(value="planNo") Integer planNo, 
			@RequestParam(value="contentId") Integer contentId, Model model,HttpSession session, AccompanyVO accompany, MemberVO member, PlannerVO planner, UserPlanVO userplan) throws Exception {	
		member = (MemberVO)session.getAttribute("userInfo");
		accompany.setUserId(member.getUserId());
		planner.setUserId(member.getUserId());
		userplan.setUserId(member.getUserId());

		model.addAttribute("cnt", g_service.countnum(contentId));
		model.addAttribute("p_list", p_service.getAllPlanner(member.getUserId()));
		model.addAttribute("contentId", contentId);
		model.addAttribute("planNo", planNo);
		model.addAttribute("u_list", u_service.getUserPlanFromPlanNo(planNo, member.getUserId()));
		model.addAttribute("startdate", u_service.getStartDate(userplan));

		return "/accompany/modify";	
	}

	@RequestMapping(value="/accompany/write/insert", method=RequestMethod.POST)
	public String insertAcc(AccompanyVO accompany, RedirectAttributes rttr) throws Exception {
		log.info("write");
		service.insertAcc(accompany);

		return "redirect:/accompany/board";
	}

	@ResponseBody
	@RequestMapping(value="/accompany/delete", method=RequestMethod.POST)
	public boolean deleteAcc(int contentId) throws Exception {
		log.info("湲� �궘�젣");
		return service.deleteAcc(contentId);
	}

	@ResponseBody
	@RequestMapping(value="/accompany/update", method=RequestMethod.POST)
	public boolean updateAcc(AccompanyVO accompany) throws Exception {
		log.info("湲� �닔�젙");
		return service.updateAcc(accompany);
	}

	@RequestMapping(value="/accompany/board_detail", method=RequestMethod.GET)
	public String boardDetail(@RequestParam(value="planNo") int planNo, @RequestParam(value="writer") String writer, @RequestParam(value="contentId") int contentId,
			Model model,HttpSession session, AccompanyVO accompany, MemberVO member, PlannerVO planner, UserPlanVO userplan) throws Exception {	
		planner.setUserId(writer);
		model.addAttribute("contentId", contentId);model.addAttribute("planNo", planNo);
		model.addAttribute("writer", writer);
		model.addAttribute("cnt", g_service.countnum(contentId));
		model.addAttribute("detail", service.readAcc(contentId));
		model.addAttribute("planner", p_service.getPlanner(planner));
		model.addAttribute("userplan", u_service.getUserPlanFromPlanNo(planNo, writer));
		model.addAttribute("idlist", g_service.readId(contentId));

		return "/accompany/board_detail";	
	}

	@ResponseBody
	@RequestMapping(value="/accompany/group/insert", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String insertInGroup(GroupAccVO group, MemberVO member, HttpSession session) throws Exception {

		int isjoin = g_service.isJoin(group);

		if(isjoin > 0) {
			log.info(isjoin + " f");
			return "�씠誘� �닔�씫�뻽�뒿�땲�떎.";
		}
		else {
			log.info(isjoin+ " t");
			service.insertInGroup(group);
			return "�닔�씫�뻽�뒿�땲�떎.";
		}
	}

	@ResponseBody
	@RequestMapping(value="/accompany/check", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String isWritten(int planNo) throws Exception {
		int result = service.iswritten(planNo);

		if(result > 0) {log.info(result+"湲�紐살뜥"); return "�씠誘� 紐⑥쭛以묒씤 �뵆�옖�엯�땲�떎.";}
		else {log.info(result+"湲��벐�윭 媛�"); return null;}
	}

	@ResponseBody
	@RequestMapping(value="/accompany/check2", method=RequestMethod.POST, produces="application/text;charset=utf8")
	public String isExist(String userId) throws Exception {
		int result = p_service.isExist(userId);

		if(result > 0) {log.info(result+"湲� �벐�윭媛�"); return null;}
		else {log.info(result+"湲� 紐살뜥 �뿬�뻾留뚮뱾怨좎�"); return "癒쇱� �뿬�뻾 �뵆�옖�쓣 留뚮뱶�꽭�슂.";}
	}

}
