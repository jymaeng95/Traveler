package com.traveler.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String calcDate(String planDate, String planTotalDate){
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(planDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		System.out.println("EndplanDate : " + df.format(cal.getTime()));

		if(Integer.parseInt(planTotalDate) != 1) {
			cal.add(Calendar.DATE, Integer.parseInt(planTotalDate));
		}
		System.out.println("Endafter: " + df.format(cal.getTime()));

		return df.format(cal.getTime());
	}

	public static String calcStartDate(String planDate, String planDay){
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(planDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		System.out.println("planDate : " + df.format(cal.getTime()));
		System.out.println(Integer.parseInt(planDay)-1);

		cal.add(Calendar.DATE, -(Integer.parseInt(planDay)-1));
		System.out.println("after: " + df.format(cal.getTime()));

		return df.format(cal.getTime());
	}
}