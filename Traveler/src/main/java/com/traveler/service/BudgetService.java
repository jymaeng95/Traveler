package com.traveler.service;

import java.util.List;

import com.traveler.domain.BudgetVO;
import com.traveler.domain.UserPlanVO;

public interface BudgetService {
	public boolean saveBudget(BudgetVO budget);
	
	public List<BudgetVO> getUserBudgetFromPlanNo(int planNo, String userId);
	
	public List<BudgetVO> getUserBudgetFromSchedule(List<UserPlanVO> schedule);
}
