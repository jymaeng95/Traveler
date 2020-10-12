package com.traveler.service;

import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.traveler.domain.BudgetVO;
import com.traveler.domain.UserPlanVO;

public interface BudgetService {
	public boolean saveBudget(BudgetVO budget);
	
	public List<BudgetVO> getUserBudgetFromPlanNo(int planNo, String userId);
	
	public List<BudgetVO> getUserBudgetFromPlanNo(int planNo);
	
	public List<BudgetVO> getUserBudgetFromSchedule(List<UserPlanVO> schedule);
	
	public List<BudgetVO> convertMapIntoBudget(Map<String,Object> data,String userId) throws ParseException;
	
	public List<BudgetVO> getAllMemberPlanNoIsPublicYes();
	
	public List<Map<String,Object>> getIsPublicBudget();

	public List<Map<String,Object>> getIsPublicCat();
	
	public List<BudgetVO> getPlanNoEqualTotalDate(String planTotalDate, String pageNo);
	
	public BudgetVO getPlanNoRecommendMaxTotal(String planTotalDate);
	
	public BudgetVO getPlanNoRecommendMinTotal(String planTotalDate);
	
	public List<Map<String,Object>> getIsPublicBudgetFromTotalDate(String planTotalDate);

	public List<Map<String,Object>> getIsPublicCatFromTotalDate(String planTotalDate);
	
	public List<BudgetVO> getBudgetIndexPaging(String pageNum);
	
	public int getCountBudgetIsPublic();
	
	public int getCountBudgetFromTotalDate(String planTotalDate);
}
