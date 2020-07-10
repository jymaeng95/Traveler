package com.traveler.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {

   private int startPage;
   private int endPage;
   private boolean prev, next;
   
   private int pageNum;
   private int total;
   
   public PageVO(String pageNum, int total) {
      this.pageNum = Integer.parseInt(pageNum);
      this.total = total;
      
      this.endPage = (int) (Math.ceil(this.pageNum / 10.0)) * 10;
      this.startPage = this.endPage-9;
      
      int realEnd = (int) (Math.ceil((total * 1.0) / 10));
      
      if(realEnd < this.endPage) {
         this.endPage = realEnd;
      }
      System.out.println(this.endPage);
      System.out.println(realEnd);
      System.out.println(total);
      this.prev = this.startPage > 1;
      this.next = this.endPage < realEnd;
   }

}