<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
   href="/resources/plan/css/sidebar.css">

<link
   href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
   rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
   rel="stylesheet">
<link href="/resources/test/font.css" rel="stylesheet">
<!-- JavaScript -->
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/esm/popper.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.js"></script>

<script
   src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx15e7f0ab6ce4456f9a97564f50cf5e2f"></script>
<script src="/resources/plan/js/modify.js"></script>
<style type="text/css">
.btn-test {
   margin: auto;
}
</style>
</head>
<body>
   <input type="hidden" name="userId" id="userId"
      value="${userInfo.userId }">
   <!-- 테스트 부트스트랩 -->
   <div class="page-wrapper chiller-theme toggled">
      <a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
         class="fas fa-bars"></i>
      </a>
      <nav id="sidebar" class="sidebar-wrapper"
         style="box-shadow: 5px 5px 5px;">
         <div class="sidebar-content">
            <div class="sidebar-brand" style="background: #258fff;">
               <button type="button" id="btn-home" class="btn btn-link"
                  style="color: #f9f9f9;" onclick="location.href='/'";>Traveler</button>

            </div>
            <div class="sidebar-brand" style="background: #258fff;">
               <button type="button" id="recommend" class="btn btn-link"
                  style="color: #f9f9f9;"onclick="">관광지 추천</button>
               <button type="button" id="result" class="btn btn-link"
                  style="color: #f9f9f9;"onclick="" disabled>검색 결과</button>
               <c:choose>
                  <c:when test="${userInfo.userId != null }">
                     <button type="button" id="bookmark" class="btn btn-link"
                        style="color: #f9f9f9;" onclick="">북마크</button>
                     <button type="button" id="myplan" class="btn btn-link"
                        style="color: #f9f9f9;" ">내 여행</button>
                  </c:when>
                  <c:otherwise>
                     <a href="/login/index"> <i class="fa fa-globe"
                        style="color: #f9f9f9;"></i> <span style="color: #f9f9f9;">Login</span>
                     </a>
                  </c:otherwise>
               </c:choose>
            </div>
            <!-- sidebar-header  -->
            <div class="sidebar-search" style="background: #258fff;">
               <div>
                  <div class="input-group" style="background: #258fff;">
                     <input type="text" class="form-control search-menu" id="search-field"
                        placeholder="검색..." style="background: #f9f9f9;">
                     <div class="input-group-append" id="btn-search">
                        <span class="input-group-text" style="background: #e9e9e9;">
                           <i class="fa fa-search" aria-hidden="true"></i>
                        </span>
                     </div>
                  </div>
               </div>
            </div>
            <!-- sidebar-search  -->
         	<div class="sidebar-menu" style="background: #f5f5f5;">
					<ul id="ul-recommend" style="padding: 10px 20px;">
						<%@include file="../plan/category/recommend.jsp"%>
					</ul>
					<ul id="ul-search" style="display : none ; padding: 10px 20px;">
						<%@include file="../plan/category/search.jsp"%>
					</ul>
					<ul id="ul-bookmark" style="display: none; padding: 10px 20px">
						<%@include file="../plan/category/bookmark.jsp"%>
					</ul>
					<ul id="ul-myPlan" data-role="listview" style="display: none; padding: 10px 20px">
						<%@include file="../plan/category/myplan.jsp"%>
					</ul>
				</div>
            <!-- sidebar-menu  -->
         </div>
         <!-- sidebar-content  -->
         <div class="sidebar-footer"
            style="background: #e9e9e9; border-top: 1px solid #ddd;">
            <c:if test="${userInfo.userId != null }">
               <a href="/mypage/message"> <i class="fa fa-envelope"></i> <span
                  class="badge badge-pill badge-success notification">7</span>
               </a>
            </c:if>
            <a href="#"> <i class="fa fa-cog"></i> <span class="badge-sonar"></span>
            </a>
            <c:if test="${userInfo.userId != null }">
               <a href="/logout">
            </c:if>
            <c:if test="${userInfo.userId == null }">
               <a href="#">
            </c:if>
            <i class="fa fa-power-off"></i> </a>
            <form action="/plan/my_plan2" method="GET">
               <button id="btn-save" type="button" class="btn btn-primary" type="reset">버튼</button>
            </form>
         </div>
      </nav>
      <!-- sidebar-wrapper  -->
      <main class="page-content">
         <div class="container" style="padding: 0px;">

            <div id="map_div"></div>

         </div>

      </main>
      <!-- page-content" -->
   </div>
   <!-- page-wrapper -->
   <!-- 해당 form jsp include에다가 넣어서 contentTypeId contentID 넣어서 보내주기  -->
   <form id="modalForm" name="modalForm" action="/plan/guide">
   </form> 
   <div id="modal-Guide" style="height: 100%; display: none;"
      class="modal fade bs-example-modal-lg in" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog modal-lg" style="height: 98%">
         <div class="modal-content" style="height: 95%; border-radius: 0px;">
            <div class="modal-header">
               <h4 class="modal-title" id="title-Guide"></h4>
               <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×</button>
            </div>
            <div class="modal-body" style="height: 94%; padding: 0px;">
               <iframe id="if_guidebook" frameborder="0" width="100%" name="guide"
                  height="100%" src="/plan/guide"></iframe>
            </div>
         </div>
      </div>
   </div>
   <c:forEach items="${planList }" var="list" varStatus="status">
      <input type="hidden" class="planData" name="planData" value='${list }'>
   </c:forEach>
   <input type="hidden" id="totaldate" value="${total_date }">
   <input type="hidden" id="plandate" value="${plan_date }">
   <input type="hidden" id="planno" value="${planNo }">
   <form id="planForm" name="planForm" action="/plan/save/modify" method="POST">
   </form>
   <div class="modal fade" tabindex="-1" role="dialog"
      aria-labelledby="mySmallModalLabel" id="addModal" aria-hidden="true">
      <div class="modal-dialog"
         style="max-width: 100%; width: auto; display: table;">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="myModalLabel">${plan_title }</h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">×</span>
               </button>

            </div>
            <div class="modal-body">
               <input type="text" id=s_title name="spot_title" readonly><br> 
               <select id="day" name="days" required autofocus>
                   <option value="1" selected>1일차</option>
               <c:forEach var="i" begin="2" end="${total_date }">
                   <option value="${i }">${i }일차</option>
               </c:forEach>
               </select>
            </div>
            <div class="modal-footer">
               <button class="btn btn-primary confirm" type="button" id="confirm">예</button>
               <button class="btn btn-primary" type="button" data-dismiss="modal">아니요</button>
            </div>
         </div>
      </div>
   </div>
   
</body>
</html>