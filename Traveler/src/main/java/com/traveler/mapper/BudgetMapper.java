package com.traveler.mapper;

import java.util.List;
import java.util.Map;

import com.traveler.domain.BudgetVO;

public interface BudgetMapper {
	public List<BudgetVO> readBudget(BudgetVO budget);
	
	public int insertAndUpdateBudget(BudgetVO budget);
	
	public int deleteBudget(BudgetVO budget);
	
	public List<BudgetVO> readAllMemberPlanNoIsPublicYes();
	
	public List<Map<String,Object>> readIsPublicBudget();
	
	public List<Map<String,Object>> readIsPublicCat();

	public String isPublicFromPlanNo(int planNo);
}
