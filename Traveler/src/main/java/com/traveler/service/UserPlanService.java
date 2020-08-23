package com.traveler.service;

import java.util.List;

import com.traveler.domain.UserPlanVO;

public interface UserPlanService {
	public boolean saveUserPlan(UserPlanVO plan) throws Exception;
	
	public List<UserPlanVO> getUserPlanFromPlanNo(UserPlanVO plan) throws Exception;

	public boolean deleteUserPlan(UserPlanVO plan) throws Exception;
	
	public boolean updateUserPlan(UserPlanVO plan) throws Exception;
	
	public List<UserPlanVO> getAllUserPlans(String userId) throws Exception;

	public List<UserPlanVO> convertUserPlan(String data,String userId) throws Exception;
	//public void convertUserPlan(List<String> data,String userId) throws Exception;
	public int getTotalCountPlan() throws Exception;
}
