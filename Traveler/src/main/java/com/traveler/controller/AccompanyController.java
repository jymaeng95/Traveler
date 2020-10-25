package com.traveler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.traveler.domain.AccompanyBoardVO;
import com.traveler.domain.AccompanyVO;
import com.traveler.domain.Criteria;
import com.traveler.domain.GuestVO;
import com.traveler.domain.HostVO;
import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.PlannerVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.AccompanyBoardService;
import com.traveler.service.AccompanyService;
import com.traveler.service.GroupAccService;
import com.traveler.service.GuestService;
import com.traveler.service.HostService;
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
   private AccompanyBoardService a_service;
   private GuestService gu_service;
   private HostService h_service;

   @RequestMapping(value="/accompany/index", method=RequestMethod.GET)
   public String AccompanyIndex(@RequestParam(value="pageNum", defaultValue="1") String pageNo, 
         Model model,HttpSession session, Criteria cri) throws Exception {   
      MemberVO member = (MemberVO)session.getAttribute("userInfo");
      List<Integer> planNo = u_service.getPlanNoAfterToday(member.getUserId());
      List<PlannerVO> planner = new ArrayList<>();
      if(planNo.size() >0) {
         for(int no : planNo) {
            planner.add(p_service.getAllPlannerFromPlanNo(no));
         }
      }
      model.addAttribute("accPlanner",planner);
      
      model.addAttribute("acc_board", a_service.readBoard(cri));
      model.addAttribute("pageMaker", new PageVO(cri, a_service.cntforpaging()));
      model.addAttribute("count", a_service.cntforpaging());

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
   public String guideSpot(HostVO hostVO, String startDate, Model model) throws Exception {
      log.info("guide");
      System.out.println();
      if(startDate != null) {
         String planDate = startDate.substring(0, 10);
         System.out.println("planDate: " + planDate + "startDate: " + startDate + "title: " + hostVO.getTitle());
         List<Map<String,Object>> acc_recommend = a_service.getRecommendAccompany(planDate, startDate, hostVO.getTitle());
         if(acc_recommend.size() > 0) {
            for(int i=0; i<acc_recommend.size(); i++) {
               System.out.println(acc_recommend.get(i));
            }
            System.out.println(acc_recommend.get(0).get("DATE_RANK"));
         }
//         System.out.println(planDate);
      }
      model.addAttribute("planNo", hostVO.getPlanNo());
      model.addAttribute("title", hostVO.getTitle());
      model.addAttribute("startDate", startDate);
      return "/accompany/register-modal";
   }
   
   ///accompany/board_deatail?planNo=3&title=휴애리&hostId=11
   @RequestMapping(value="/accompany/board_detail", method=RequestMethod.GET)
   public String boardDetail(@RequestParam(value="planNo") int planNo, @RequestParam(value="title") String title, @RequestParam(value="acc_bno") int acc_bno,
         @RequestParam(value="hostId") String hostId, Model model, GuestVO guest, HostVO host) throws Exception {   

      model.addAttribute("userplan", u_service.getPlanForAccompany(planNo));
      model.addAttribute("title", title);
      model.addAttribute("acc", a_service.readAcc(acc_bno));
      model.addAttribute("guestid",gu_service.readId(guest));
      model.addAttribute("host",h_service.readHost(host));
      
      model.addAttribute("planNo", host.getPlanNo());
      model.addAttribute("title", host.getTitle());

      return "/accompany/board_detail";   
   }

   @ResponseBody
   @RequestMapping(value="/accompany/guest/insert", method=RequestMethod.POST, produces="application/text;charset=utf8")
   public String insertGuest(GuestVO guest, HttpSession session) throws Exception {
      int isJoin = gu_service.isJoin(guest);
      if(isJoin > 0) {
         System.out.println("이미 join");
         return "이미 수락했습니다.";
      }
      else {
         System.out.println("join 가능");
         gu_service.insertGuest(guest);
         return "수락했습니다.";
      }
      
      
   }

   //동행 모집 폼 전송
   @RequestMapping(value="/accompany/recruit", method=RequestMethod.POST)
   public String insertAcc(AccompanyBoardVO accompany, HostVO host, RedirectAttributes rttr, 
		   HttpSession session, UserPlanVO plan) throws Exception {
      log.info("紐⑥쭛湲� �옉�꽦");
//      System.out.println(accompany.getStartDate());
      MemberVO member = (MemberVO)session.getAttribute("userInfo");
      accompany.setHostId(member.getUserId());
      host.setHostId(member.getUserId());
      
      System.out.println("check" + host.getHostId() + " " + host.getPlanNo() + ' ' + host.getTitle() + " " + accompany.getTitle()
      + accompany.getBoardTitle());
	  plan.setIsacc("Y");
	  u_service.updateisacc(plan);
	  a_service.insertAcc(accompany);
	  
      int acc_bno = a_service.readAccBno(accompany);
      host.setAccBno(acc_bno);
      h_service.insertHost(host);

      return "redirect:/accompany/index";
   }

   
   @ResponseBody
   @RequestMapping(value="/accompany/delete", method=RequestMethod.POST)
   public boolean deleteAcc(HostVO host, UserPlanVO plan) throws Exception {
      log.info("delete acc");
      plan.setIsacc("N");
      u_service.updateisacc(plan);
     
      return h_service.deleteHost(host);
   }
   
   @RequestMapping(value="/accompany/update", method=RequestMethod.POST)
   public String updateAcc(AccompanyBoardVO accompany, HostVO host, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
      log.info("update acc/host");
      boolean result = h_service.updateHost(host);
      boolean result2 = a_service.updateAcc(accompany);
      
      
      rttr.addAttribute("planNo", host.getPlanNo());
      rttr.addAttribute("title", host.getTitle());
      rttr.addAttribute("hostId", host.getHostId());
      rttr.addAttribute("acc_bno", accompany.getAccBno());
      
      if(result == true && result2 == true) return "redirect:/accompany/board_detail"; 
      else return "error";
   }
   
   
   
   
   //////////////////////////////////////////////////////
   
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


   @ResponseBody
   @RequestMapping(value="/accompany/check", method=RequestMethod.POST, produces="application/text;charset=utf8")
   public String isWritten(int planNo) throws Exception {
      int result = service.iswritten(planNo);

      if(result > 0) {log.info(result+"湲 紐살뜥"); return " 씠誘  紐⑥쭛以묒씤  뵆 옖 엯 땲 떎.";}
      else {log.info(result+"湲  벐 윭 媛 "); return null;}
   }

   @ResponseBody
   @RequestMapping(value="/accompany/check2", method=RequestMethod.POST, produces="application/text;charset=utf8")
   public String isExist(String userId) throws Exception {
      int result = p_service.isExist(userId);

      if(result > 0) {log.info(result+"湲   벐 윭媛 "); return null;}
      else {log.info(result+"湲  紐살뜥  뿬 뻾留뚮뱾怨좎 "); return "癒쇱   뿬 뻾  뵆 옖 쓣 留뚮뱶 꽭 슂.";}
   }

}