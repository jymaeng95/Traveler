package com.traveler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		return mapper.insertBudget(budget) > 0;
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
			onePlan.setDescript(schedule.get(i).getDescript());
			budget.add(onePlan);
		}
		return budget;
	}
	
	@Override
	public List<BudgetVO> convertJSONintoBudget(Map<String,Object> data,String userId) {
		List<BudgetVO> budget = new ArrayList<>();
		
		int planNo = Integer.parseInt(data.get("planNo").toString());
		int total = Integer.parseInt(data.get("total").toString());
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
			if(oneTrans.get("descript")==null)
				oneBudget.setDescript("");
			else
				oneBudget.setDescript(oneTrans.get("descript").toString());
			budget.add(oneBudget);
		}
		return budget;
	}
}
