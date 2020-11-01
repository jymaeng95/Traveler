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
import com.traveler.domain.Criteria;
import com.traveler.domain.GuestVO;
import com.traveler.domain.HostVO;
import com.traveler.domain.MemberVO;
import com.traveler.domain.PageVO;
import com.traveler.domain.PlannerVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.service.AccompanyBoardService;
import com.traveler.service.GuestService;
import com.traveler.service.HostService;
import com.traveler.service.PlannerService;
import com.traveler.service.UserPlanService;
import com.traveler.util.DateUtils;
import com.traveler.util.RandomAcc;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class AccompanyController {
   private PlannerService p_service;
   private UserPlanService u_service;
   private AccompanyBoardService a_service;
   private GuestService gu_service;
   private HostService h_service;

   @RequestMapping(value="/accompany/index", method=RequestMethod.GET)
   public String AccompanyIndex(@RequestParam(value="pageNum", defaultValue="1") String pageNo, AccompanyBoardVO accompany, GuestVO guest,
         Model model,HttpSession session, Criteria cri) throws Exception {   
      MemberVO member = (MemberVO)session.getAttribute("userInfo");
      List<Integer> planNo = u_service.getPlanNoAfterToday(member.getUserId());
      List<PlannerVO> planner = new ArrayList<>();
      if(planNo.size() >0) {
         for(int no : planNo) {
            if(u_service.moreIsAcc(no))
               planner.add(p_service.getAllPlannerFromPlanNo(no));
         }
      }
      model.addAttribute("accPlanner",planner);
      
      model.addAttribute("acc_board", a_service.readBoard(cri));
      model.addAttribute("pageMaker", new PageVO(cri, a_service.cntforpaging()));
      model.addAttribute("count", a_service.cntforpaging());
   
      List<Integer> bno = a_service.readbno(pageNo);
      List<HostVO> list = new ArrayList<>();
      for(int i=0;i<bno.size();i++) {
         log.info(bno.get(i));
         list.add(h_service.readnum(bno.get(i)));
      }
      model.addAttribute("numlist", list);
            
      model.addAttribute("myacc",a_service.readmyAcc(member.getUserId()));
      List<AccompanyBoardVO> boardData = new ArrayList<>();
      for(GuestVO gs : gu_service.readguestAcc(member.getUserId())) {
         boardData.add(a_service.readguestAcc(gs.getAccBno()));
      }
      model.addAttribute("guestBoard",boardData);
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
      List<Map<String,Object>> acc_sortRecommend = new ArrayList<>();
      if(startDate != null) {
         String planDate = startDate.substring(0, 10);
         System.out.println("planDate: " + planDate + "startDate: " + startDate + "title: " + hostVO.getTitle());
         List<Map<String,Object>> acc_recommend = a_service.getRecommendAccompany(planDate, startDate, hostVO.getTitle());
         System.out.println(acc_sortRecommend);
         if(acc_recommend.size() > 0) {
            if(acc_recommend.size() > 3) {
               RandomAcc rand_acc = new RandomAcc();
               acc_sortRecommend = rand_acc.getRandomAccompany(acc_recommend);
//               System.out.println(acc_sortRecommend.size());
                for(int i=0; i<acc_sortRecommend.size(); i++) {
                   System.out.println(acc_sortRecommend.get(i));
                }
            }else {
               for(int i=0; i<acc_recommend.size(); i++) {
                  acc_sortRecommend.add(acc_recommend.get(i));
               }
               for(int i=0; i<acc_sortRecommend.size(); i++) {
                   System.out.println(acc_sortRecommend.get(i));
                }
            }
            
//            for(int i=0; i<acc_recommend.size(); i++) {
//               System.out.println(acc_recommend.get(i));
//            }
//            System.out.println(acc_recommend.get(0).get("DATE_RANK"));

            
         }
//         System.out.println(planDate);
      }
      model.addAttribute("planNo", hostVO.getPlanNo());
      model.addAttribute("title", hostVO.getTitle());
      model.addAttribute("startDate", startDate);
      model.addAttribute("accRandRecommend", acc_sortRecommend);
      return "/accompany/register-modal";
   }

   
   ///accompany/board_deatail?planNo=3&title=휴애리&hostId=11
   @RequestMapping(value="/accompany/board_detail", method=RequestMethod.GET)
   public String boardDetail(@RequestParam(value="planNo") int planNo, @RequestParam(value="title") String title, @RequestParam(value="acc_bno") int acc_bno,
         @RequestParam(value="hostId") String hostId, Model model, GuestVO guest, HostVO host, HttpSession session) throws Exception {   

      model.addAttribute("userplan", u_service.getPlanForboard(planNo));
      model.addAttribute("title", title);
      model.addAttribute("acc", a_service.readAcc(acc_bno));
      model.addAttribute("host",h_service.readHost(host));
      
      model.addAttribute("planNo", host.getPlanNo());
      model.addAttribute("title", host.getTitle());
      
      MemberVO member = (MemberVO)session.getAttribute("userInfo");
      guest.setGuestId(member.getUserId());
      int isGuest = gu_service.isJoin(guest);
      if(isGuest > 0) model.addAttribute("isguest","y" );
      else model.addAttribute("isguest","n" );

      return "/accompany/board_detail";   
   }

   @ResponseBody
   @RequestMapping(value="/accompany/guest/insert", method=RequestMethod.POST, produces="application/text;charset=utf8")
   public String insertGuest(GuestVO guest, HostVO host, HttpSession session) throws Exception {
      int isJoin = gu_service.isJoin(guest);
      if(isJoin > 0) return "이미 수락했습니다.";
      else {
         gu_service.insertGuest(guest);
         h_service.addcurperson(host);
         return "수락했습니다.";
      }
   }

   //동행 모집 폼 전송
   @RequestMapping(value="/accompany/recruit", method=RequestMethod.POST)
   public String insertAcc(AccompanyBoardVO accompany, HostVO host, RedirectAttributes rttr, 
         HttpSession session, UserPlanVO plan) throws Exception {
      log.info("紐⑥쭛湲   옉 꽦");
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
   public boolean deleteAcc(AccompanyBoardVO accompany, UserPlanVO plan) throws Exception {
      log.info("delete acc");
      plan.setIsacc("N");
      u_service.updateisacc(plan);
     
      return a_service.deleteAcc(accompany);
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
   
   @ResponseBody
   @RequestMapping(value="/accompany/guest/delete", method=RequestMethod.POST)
   public boolean deleteGuest(GuestVO guest, HostVO host) throws Exception {
      log.info("delete guest");
      h_service.mincurperson(host);
      return gu_service.deleteGuest(guest);
   }
   
}