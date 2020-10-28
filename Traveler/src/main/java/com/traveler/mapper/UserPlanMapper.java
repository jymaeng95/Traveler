package com.traveler.mapper;

import java.util.List;

import com.traveler.domain.PlannerVO;
import com.traveler.domain.UserPlanVO;

public interface UserPlanMapper {
   public int insertPlanFirst(UserPlanVO plan);

   public int finalPlan(UserPlanVO plan);

   public List<UserPlanVO> readPlans(UserPlanVO plan);

   public List<UserPlanVO> readSchedule(UserPlanVO plan);

   public int deletePlan(UserPlanVO plan);

   public int updatePlan(UserPlanVO plan);

   public int getCountPlanNo();

   public List<UserPlanVO> readAllPlans(String userId);

   public UserPlanVO readPlanDate(UserPlanVO plan);

   public int insertModifyPlanFirst(UserPlanVO plan);

   public int deleteSchedule(UserPlanVO plan);
   
   public UserPlanVO getStartDate(UserPlanVO plan);
   
   public UserPlanVO readStartDate(UserPlanVO plan);
   
   public List<Integer> readPlanNoAfterToday(String userId);
   
   public List<UserPlanVO> readPlanForAccompany(int planNo);
   
   public int updateisacc(UserPlanVO plan);
   
   public int countIsAcc(int planNo);
   
   public List<UserPlanVO> readPlanForboard(int planNo);
}