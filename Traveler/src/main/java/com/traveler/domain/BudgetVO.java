package com.traveler.domain;

import lombok.Data;

@Data
public class BudgetVO {
	private int budget_no;
	private int planNo;
	private String userId;
	private String title;
	private String cat;
	private int income;
	private int expend;
	private int total;
	private String planDate;
	private String planTotalDate;
	private String descript;
	private String is_public;
	private String reg_date;
	private PlannerVO plannerVO;
}
