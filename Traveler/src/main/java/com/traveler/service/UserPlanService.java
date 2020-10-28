package com.traveler.service;

import java.util.List;

import com.traveler.domain.UserPlanVO;

public interface UserPlanService {
   public boolean saveUserPlan(UserPlanVO plan) throws Exception;

   public boolean saveSchedule(UserPlanVO plan) throws Exception;

   public List<UserPlanVO> getUserPlanFromPlanNo(int planNo, String userId) throws Exception;

   public List<UserPlanVO> getUserScheduleFromPlanNo(int planNo, String userId) throws Exception;

   public boolean deleteUserPlan(UserPlanVO plan) throws Exception;

   public boolean updateUserPlan(UserPlanVO plan) throws Exception;

   public List<UserPlanVO> getAllUserPlans(String userId) throws Exception;

   public List<UserPlanVO> convertUserPlan(String data,String userId) throws Exception;

   public List<UserPlanVO> convertSchedule(String data) throws Exception;

   public int getTotalCountPlan() throws Exception;

   public UserPlanVO getUserPlanDate(String userId, int planNo) throws Exception;

   public boolean saveUserModifyPlan(UserPlanVO plan) throws Exception;

   public boolean deleteUserSchedule(UserPlanVO plan) throws Exception;
   
   public UserPlanVO getStartDate(UserPlanVO plan) throws Exception; 

   public UserPlanVO getPlanDate(int planNo) throws Exception; 
   
   public List<Integer> getPlanNoAfterToday(String userId);
   
   public List<UserPlanVO> getPlanForAccompany(int planNo);
   
   public boolean updateisacc(UserPlanVO plan) throws Exception;
   
   public boolean moreIsAcc(int planNo);
   
   public List<UserPlanVO> getPlanForboard(int planNo);
}