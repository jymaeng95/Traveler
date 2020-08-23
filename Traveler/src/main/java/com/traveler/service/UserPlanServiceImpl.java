package com.traveler.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.traveler.domain.UserPlanVO;
import com.traveler.mapper.UserPlanMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class UserPlanServiceImpl implements UserPlanService{
	private UserPlanMapper mapper;
	
	@Override
	public boolean saveUserPlan(UserPlanVO plan) throws Exception {
		log.info(plan);
		int resultCount = mapper.insertPlan(plan);
		return resultCount>0;
	}

	@Override
	public List<UserPlanVO> getUserPlanFromPlanNo(UserPlanVO plan) throws Exception {
		// TODO Auto-generated method stub
		log.info(plan);
		return mapper.readPlans(plan);
	}

	@Override
	public boolean deleteUserPlan(UserPlanVO plan) throws Exception {
		// TODO Auto-generated method stub
		log.info(plan);
		int resultCount = mapper.deletePlan(plan);
		return resultCount > 0;
	}

	@Override
	public boolean updateUserPlan(UserPlanVO plan) throws Exception {
		// TODO Auto-generated method stub
		log.info(plan);
		int resultCount = mapper.updatePlan(plan);
		return resultCount > 0;
	}

	@Override
	public List<UserPlanVO> getAllUserPlans(String userId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.readAllPlans(userId);
	}
	
	public List<UserPlanVO> convertUserPlan(String data,String userId) throws ParseException{
		List<UserPlanVO> planInfo = new ArrayList<UserPlanVO>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(data);
		JSONObject jobj = (JSONObject) obj;
		JSONArray arr = (JSONArray) jobj.get("data");		
		log.info(arr.get(0));
		for(int i=0;i<arr.size();i++) {
			UserPlanVO plan = new UserPlanVO();
			JSONObject arrObj = (JSONObject) arr.get(i);
			plan.setPlanNo(Integer.parseInt(arrObj.get("planno").toString()));
			plan.setUserId(userId);
			plan.setTitle(arrObj.get("title").toString());
			if(arrObj.get("contentId")!= null)
				plan.setContentId(arrObj.get("contentId").toString());
			else plan.setContentId("");
			if(arrObj.get("contentId")!= null)
				plan.setContentTypeId(arrObj.get("contentTypeId").toString());
			else plan.setContentTypeId("");
			plan.setAddr(arrObj.get("addr").toString());
			plan.setImg_src(arrObj.get("img").toString());
			JSONObject lonlat = (JSONObject) arrObj.get("lonlat");
			plan.setMapX(lonlat.get("_lat").toString());
			plan.setMapY(lonlat.get("_lng").toString());
			plan.setPlanDate(Date.valueOf(arrObj.get("planDate").toString()));
			plan.setPlanTitle(arrObj.get("planTitle").toString());
			plan.setPlanDay(arrObj.get("planDay").toString());
			plan.setPlanTotalDate(arrObj.get("planTotalDate").toString());
			//			planInfo.add(plan);
			log.info(plan);
			planInfo.add(plan);
		}
		return planInfo;
	}

	@Override
	public int getTotalCountPlan() throws Exception {
		return mapper.getCountPlanNo();
	}
	
}
