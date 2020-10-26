package com.traveler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RandomAcc {

	public List<Map<String,Object>> getRandomAccompany(List<Map<String,Object>> acc_rand) {
		List<Map<String,Object>> acc_randRecommend = new ArrayList<>();

		Set<Integer> set = new HashSet<>(); 
		if(String.valueOf(acc_rand.get(1).get("DATE_RANK")).equals("1")) {
			//			System.out.println("1");
			if(String.valueOf(acc_rand.get(2).get("DATE_RANK")).equals("1")) {
				//				System.out.println("11");
				while (set.size() < 3) { 
					Double d = Math.random() * acc_rand.size(); 
					set.add(d.intValue()); 
				}
				List<Integer> list = new ArrayList<>(set); 
				Collections.sort(list);
				for(int i=0; i<3; i++) {
					acc_randRecommend.add(acc_rand.get(list.get(i)));
				}
			}else {
				//				System.out.println("1e");
				while (set.size() < 1) { 
					Double d = (Math.random() * (acc_rand.size()-2)) + 2; 
					set.add(d.intValue()); 
				}
				List<Integer> list = new ArrayList<>(set); 
				Collections.sort(list);
				for(int i=0; i<2; i++) {
					acc_randRecommend.add(acc_rand.get(i));
				}
				acc_randRecommend.add(acc_rand.get(list.get(0)));
			}
		}
		if(String.valueOf(acc_rand.get(1).get("DATE_RANK")).equals("2")) {
			//			System.out.println("2");
			if(String.valueOf(acc_rand.get(2).get("DATE_RANK")).equals("2")) {
				//				System.out.println("22");
				while (set.size() < 2) { 
					Double d = (Math.random() * (acc_rand.size()-1)) + 1; 
					set.add(d.intValue()); 
				}
				List<Integer> list = new ArrayList<>(set); 
				Collections.sort(list);
				acc_randRecommend.add(acc_rand.get(0));
				for(int i=1; i<3; i++) {
					acc_randRecommend.add(acc_rand.get(list.get(i)));
				}
			}else {
				//				System.out.println("2e");
				while (set.size() < 1) { 
					Double d = (Math.random() * (acc_rand.size()-2)) + 2; 
					set.add(d.intValue()); 
				}
				List<Integer> list = new ArrayList<>(set); 
				Collections.sort(list);
				for(int i=0; i<2; i++) {
					acc_randRecommend.add(acc_rand.get(i));
				}
				acc_randRecommend.add(acc_rand.get(list.get(0)));
			}
		}
		return acc_randRecommend;
	}
}
