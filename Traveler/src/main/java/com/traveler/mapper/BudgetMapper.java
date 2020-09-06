package com.traveler.mapper;

import java.util.List;

import com.traveler.domain.BudgetVO;

public interface BudgetMapper {
	public int insertBudget(BudgetVO budget);
	
	public List<BudgetVO> readBudget(BudgetVO budget);
	
	public int updateBudget(BudgetVO budget);
	
	public int deleteBudget(BudgetVO budget);
	
}
