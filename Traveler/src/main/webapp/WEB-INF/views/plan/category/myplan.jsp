<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<li class="header-menu" id="myPlanList"><span>내 여행</span></li>
<form method="GET" name="myPlanForm" id="myPlanForm">
   <c:forEach var="i" begin="1" end="${total_date }">
      <li class="sidebar-dropdown" id="day${i }">
            <a href="javascript:void(0)">
              <span>${i }일차</span>
            </a>
            
            <div class="sidebar-submenu" style="background: #f5f5f5;">
              <ul class="sub-plan${i }"> <!-- 수정 -->
              </ul>
            </div>
            
        </li>
   </c:forEach>

</form>