package com.traveler.service;

import java.util.ArrayList;
import java.util.List;

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
	
}
