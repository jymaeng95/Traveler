package com.traveler.mapper;

import java.util.List;

import com.traveler.domain.BudgetVO;

public interface BudgetMapper {
	public List<BudgetVO> readBudget(BudgetVO budget);
	
	public int insertAndUpdateBudget(BudgetVO budget);
	
	public int deleteBudget(BudgetVO budget);
	
	public List<BudgetVO> readAllMemberPlanNoIsPublicYes();
}
