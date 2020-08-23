package com.traveler.mapper;

import java.util.List;

import com.traveler.domain.UserPlanVO;

public interface UserPlanMapper {
	//플랜 삽입
	public int insertPlan(UserPlanVO plan);
	//플랜 불러오기 
	public List<UserPlanVO> readPlans(UserPlanVO plan);
	//플랜 삭제 
	public int deletePlan(UserPlanVO plan);
	//플랜 업데이트 
	public int updatePlan(UserPlanVO plan);
	
	public int getCountPlanNo();
	//회원의 모든 플랜 불러오
	public List<UserPlanVO> readAllPlans(String userId);
}
