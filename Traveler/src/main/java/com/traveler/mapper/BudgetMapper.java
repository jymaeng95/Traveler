package com.traveler.mapper;

import java.util.List;
import java.util.Map;

import com.traveler.domain.BudgetVO;
import com.traveler.domain.UserPlanVO;

public interface BudgetMapper {
	public List<BudgetVO> readBudget(BudgetVO budget);
	
	public List<BudgetVO> readBudgetFromPlanNo(BudgetVO budget);
	
	public int insertAndUpdateBudget(BudgetVO budget);
	
	public int deleteBudget(BudgetVO budget);
	
	public List<BudgetVO> readAllMemberPlanNoIsPublicYes();
	
	public List<Map<String,Object>> readIsPublicBudget();
	
	public List<Map<String,Object>> readIsPublicCat();
	
	public List<BudgetVO> readPlanNoEqualTotalDate(Map<String,Object> data);
	
	public BudgetVO readPlanNoRecommendMaxTotal(String planTotalDate);
	
	public BudgetVO readPlanNoRecommendMinTotal(String planTotalDate);
	
	public List<Map<String,Object>> readIsPublicBudgetFromTotalDate(String planTotalDate);
	
	public List<Map<String,Object>> readIsPublicCatFromTotalDate(String planTotalDate);
	
	public List<BudgetVO> budgetIndexPaging(String pageNum);
	
	public int countBudgetIsPublic();
	
	public int countBudgetFromTotalDate(String planTotalDate);
}
