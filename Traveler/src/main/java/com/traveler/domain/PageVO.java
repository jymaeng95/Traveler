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
   
   public PageVO(String pageNum, int total, int count) {
      this.pageNum = Integer.parseInt(pageNum);
      this.total = total;
      
      this.endPage = (int) (Math.ceil((this.pageNum * 1.0) / count)) * count;
      this.startPage = this.endPage-(count - 1);
      
      int realEnd = (int) (Math.ceil((total * 1.0) / count));
      
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