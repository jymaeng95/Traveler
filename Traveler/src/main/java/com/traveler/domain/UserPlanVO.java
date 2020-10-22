package com.traveler.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class UserPlanVO {
   private int planNo;
   private String userId;
   private String title;
   private String contentId;
   private String contentTypeId;
   private String addr;
   private String img_src;
   private String mapX;
   private String mapY;
   private String planDate;
   private String planDay;
   private String planTotalDate;
   private String startDate;
   private String endDate;
   private String descript;
   private String overview;
   private String is_insertAfter;
   private String isacc;
   private PlannerVO plannerVO;
}