package com.traveler.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traveler.domain.PlannerVO;
import com.traveler.mapper.PlannerMapper;
import com.traveler.mapper.UserPlanMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class PlannerServiceImpl implements PlannerService {
	
	private PlannerMapper mapper;
	@Override
	public boolean savePlanner(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.insertNewPlanner(planner) > 0;
	}

	@Override
	public int getPlanNo() {
		// TODO Auto-generated method stub
		return mapper.readPlanNo();
	}

	@Override
	public List<PlannerVO> getAllPlanner(String userId) {
		// TODO Auto-generated method stub
		return mapper.readAllPlanner(userId);
	}

	@Override
	public PlannerVO getPlanner(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.readPlanner(planner);
	}

	@Override
	public boolean updatePlannerImg(PlannerVO planner) {
		// TODO Auto-generated method stub
		return mapper.updatePlannerImg(planner) > 0;
	}

	@Override
	public boolean updatePlanner(PlannerVO planner, String userId) {
		// TODO Auto-generated method stub
		planner.setUserId(userId);
		return mapper.updatePlanner(planner)>0;
	}
	
}
