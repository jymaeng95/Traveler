package com.traveler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.traveler.domain.BudgetVO;
import com.traveler.domain.UserPlanVO;
import com.traveler.mapper.BudgetMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService{
	private BudgetMapper mapper;

	@Override
	public boolean saveBudget(BudgetVO budget) {
		// TODO Auto-generated method stub
		return mapper.insertAndUpdateBudget(budget) > 0;
	}

	@Override
	public List<BudgetVO> getUserBudgetFromPlanNo(int planNo, String userId) {
		// TODO Auto-generated method stub
		BudgetVO budget = new BudgetVO();
		budget.setPlanNo(planNo);
		budget.setUserId(userId);
		return mapper.readBudget(budget);
	}

	@Override
	public List<BudgetVO> getUserBudgetFromSchedule(List<UserPlanVO> schedule) {
		List<BudgetVO> budget = new ArrayList<>();
		for(int i=0;i<schedule.size();i++) {
			BudgetVO onePlan = new BudgetVO();
			onePlan.setPlanNo(schedule.get(i).getPlanNo());
			onePlan.setUserId(schedule.get(i).getUserId());
			onePlan.setTitle(schedule.get(i).getTitle());
			onePlan.setCat("");
			onePlan.setIncome(0);
			onePlan.setExpend(0);
			onePlan.setTotal(0);
			onePlan.setPlanDate(schedule.get(i).getPlanDate());
			onePlan.setPlanTotalDate(schedule.get(i).getPlanTotalDate());
			onePlan.setIs_public("N");
			if(schedule.get(i).getDescript()==null)
				onePlan.setDescript("");
			else
				onePlan.setDescript(schedule.get(i).getDescript());
			budget.add(onePlan);
		}
		return budget;
	}

	@Override
	public List<BudgetVO> convertMapIntoBudget(Map<String,Object> data,String userId) throws ParseException {
		List<BudgetVO> budget = new ArrayList<>();
		
		int planNo = Integer.parseInt(data.get("planNo").toString());
		int total = Integer.parseInt(data.get("total").toString());
		String is_public = data.get("is_public").toString();
		String planTotalDate = data.get("planTotalDate").toString();
		List<Map> trans = (List<Map>) data.get("transactions");
		for(int i=0;i<trans.size();i++) {
			Map<String,Object> oneTrans = trans.get(i);
			BudgetVO oneBudget = new BudgetVO();
			oneBudget.setPlanNo(planNo);
			oneBudget.setUserId(userId);
			oneBudget.setTitle(oneTrans.get("title").toString());
			if(oneTrans.get("cat")==null)
				oneBudget.setCat("");
			else
				oneBudget.setCat(oneTrans.get("cat").toString());
			oneBudget.setIncome(Integer.parseInt(oneTrans.get("income").toString()));
			oneBudget.setExpend(Integer.parseInt(oneTrans.get("expend").toString()));
			oneBudget.setTotal(total);
			oneBudget.setPlanDate(oneTrans.get("planDate").toString());
			oneBudget.setPlanTotalDate(planTotalDate);
			if(oneTrans.get("descript")==null)
				oneBudget.setDescript("");
			else
				oneBudget.setDescript(oneTrans.get("descript").toString());
			oneBudget.setIs_public(is_public);
			budget.add(oneBudget);
		}
		
		return budget;
	}

	@Override
	public List<BudgetVO> getAllMemberPlanNoIsPublicYes() {
		// TODO Auto-generated method stub
		
		return mapper.readAllMemberPlanNoIsPublicYes();
	}

	@Override
	public List<Map<String,Object>> getIsPublicBudget() {
		// TODO Auto-generated method stub
		return mapper.readIsPublicBudget();
	}

	@Override
	public List<Map<String, Object>> getIsPublicCat() {
		// TODO Auto-generated method stub
		return mapper.readIsPublicCat();
	}

	@Override
	public List<BudgetVO> getPlanNoEqualTotalDate(String planTotalDate, String pageNo) {
		// TODO Auto-generated method stub
		Map<String,Object> data = new HashMap<>();
		data.put("planTotalDate",planTotalDate);
		data.put("pageNum",pageNo);
		return mapper.readPlanNoEqualTotalDate(data);
	}

	@Override
	public BudgetVO getPlanNoRecommendMaxTotal(String planTotalDate) {
		// TODO Auto-generated method stub
		return mapper.readPlanNoRecommendMaxTotal(planTotalDate);
	}

	@Override
	public BudgetVO getPlanNoRecommendMinTotal(String planTotalDate) {
		// TODO Auto-generated method stub
		return mapper.readPlanNoRecommendMinTotal(planTotalDate);
	}

	@Override
	public List<Map<String, Object>> getIsPublicBudgetFromTotalDate(String planTotalDate) {
		// TODO Auto-generated method stub
		return mapper.readIsPublicBudgetFromTotalDate(planTotalDate);
	}

	@Override
	public List<Map<String, Object>> getIsPublicCatFromTotalDate(String planTotalDate) {
		// TODO Auto-generated method stub
		return mapper.readIsPublicCatFromTotalDate(planTotalDate);
	}

	@Override
	public List<BudgetVO> getUserBudgetFromPlanNo(int planNo) {
		// TODO Auto-generated method stub
		BudgetVO budget = new BudgetVO();
		budget.setPlanNo(planNo);
		return mapper.readBudgetFromPlanNo(budget);
	}

	@Override
	public List<BudgetVO> getBudgetIndexPaging(String pageNum) {
		// TODO Auto-generated method stub
		return mapper.budgetIndexPaging(pageNum);
	}

	@Override
	public int getCountBudgetIsPublic() {
		// TODO Auto-generated method stub
		return mapper.countBudgetIsPublic();
	}

	@Override
	public int getCountBudgetFromTotalDate(String planTotalDate) {
		// TODO Auto-generated method stub
		return mapper.countBudgetFromTotalDate(planTotalDate);
	}
	
}
