<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="/resources/plan/css/index.css" rel="stylesheet">
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/plan/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.rawgit.com/jquery/jquery-mousewheel/master/jquery.mousewheel.min.js"></script>
<!-- <script src="/resources/plan/js/index.js"></script> -->
<title>Insert title here</title>
</head>
<body>
   <%@include file="../includes/sidebar.jsp"%>
   <input type="hidden" name="userId" id="userId"
      value="${userInfo.userId }">
   <main class="page-content">
      <div class="container" style="padding: 0">
         <section class="bg-light" style="margin-top: 15px;">
            <div class="container"
               style="overflow-x: scroll; padding-top: 0px; margin-top: 0px; margin-bottom: 10px;">
               <h2 class="pt-3 pb-3"
                  style="padding-left: 20px; font-size: 18pt; font-weight: 300; color: #696969">
                  내 제주도 여행</h2>
               <!-- c태그로 if문으로 제어  -->
               <c:if test="${userInfo.userId == null }">
                  <div
                     style="text-align: center; padding-top: 40px; padding-bottom: 40px; color: #c0c0c0; font-size: 12pt">
                     새로운 여행을 만들어 보세요!</div>
               </c:if>
               <c:if test="${userInfo.userId != null }">
                  <c:if test="${resultData.size() == 0 }">
                     <div
                        style="text-align: center; padding-top: 40px; padding-bottom: 40px; color: #c0c0c0; font-size: 12pt">
                        새로운 여행을 만들어 보세요!</div>
                  </c:if>
                  <c:if test="${resultData.size() != 0 }">

                     
                        <div class="scrolling-wrapper-flexbox">
                           <c:forEach items="${resultData }" var="list">
                              <div class="planner planner--big"
                                 style="margin: 10px 30px 20px 30px;">
                                 <div class="planner__image">
                                    <img class="planner_image" src="${list.planInfo.planImg}"
                                       style="width: 100%; height: 100%"></img>
                                 </div>
                                 <h2 class="planner__title">
                                    <c:out
                                       value="${list.planInfo.planTitle }(${list.planTotalDate }일간의 여행)"></c:out>
                                 </h2>
                                 <p class="planner__info">
                                    <c:out value="${list.planInfo.info }"></c:out>
                                    <br>
                                    <c:out value="${list.planStartDate } ~ ${list.planEndDate }"></c:out>
                                    <br>
                                 </p>
                                 <div class="planner__action-bar">
                                    <button class="planner__button btnModify"
                                       value="${list.planInfo.planNo }" style="float: left;">Modify</button>
                                    <button class="planner__button btnRead"
                                       value="${list.planInfo.planNo }" style="position : absolute; left:40%;">Read</button>
                                    <button class="planner__button btnDelete"
                                       value="${list.planInfo.planNo }" style="float: right;">Delete</button>
                                 </div>
                              </div>

                              <%--    <div style="text-align: center; font-size: 12pt;">
                              <a class="planLoad" id="${list.planNo }"
                                 href="javascript:void(0);"> <span class="plan_title"><c:out
                                       value="${list.planTitle }"></c:out></span></a>
                           </div> --%>

                           </c:forEach>
                        </div>
                     <form action="/plan/plan/detail" method="GET" id="modifyForm">
                        <input type="hidden" name="when" value="modify">
                     </form>
                     <form action="/plan/plan/readPlan" method="GET" id="readForm">
                     </form>
                     <form action="/plan/plan/deletePlan" method="GET" id="deleteForm">
                     </form>
                  </c:if>

               </c:if>
            </div>
         </section>
         <section
            style="padding-top: 30px; padding-bottom: 20px; margin-top: 0px;">
            <div class="container pt-3">
               <div class="row">
                  <!-- left content -->
                  <div class="col-lg-6">
                     <img class="plan_img"
                        src="/resources/plan/img/planMain.png">
                  </div>
                  <!-- right content -->
                  <div class="jumbotron bg-light text-center col-lg-5 ml-auto">
                     <h2 style="font-size: 18pt; font-weight: 300; color: #696969">
                        Traveler로</h2>
                     <h2 style="font-size: 18pt; font-weight: 300; color: #696969">
                        새로운 여행을 계획해 봅시다.</h2>
                     <div class="row">
                        <div class="col-sm-6">
                           <button type="button" id="new_plan"
                              style="border-radius: 30px; margin-right: 5px; text-align: center; margin-top: 70%; width: 100%; text-decoration: none; font-weight: 300; color: #000; font-size: 14pt; display: inline-block; padding: 10px 20px; border: 1px solid">
                              <!-- href="/plan/create" -->
                              새 로운 여행
                           </button>
                        </div>
                        <div class="col-sm-6">
                           <!-- 임시 마이플랜으로 이동  -->
                           <a href="/spot/spot"
                              style="border-radius: 30px; margin-right: 5px; text-align: center; margin-top: 70%; width: 100%; text-decoration: none; font-weight: 300; color: #000; font-size: 14pt; display: inline-block; padding: 10px 20px; border: 1px solid">스팟
                              찾기</a>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </section>
      </div>
   </main>

   <div class="modal fade" tabindex="-1" role="dialog"
      aria-labelledby="mySmallModalLabel" id="myModal" aria-hidden="true">
      <div class="modal-dialog"
         style="max-width: 100%; width: auto; display: table;">
         <div class="modal-content">
            <div class="modal-header">
               <h4 class="modal-title" id="myModalLabel">새 여행 만들기</h4>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">×</span>
               </button>


            </div>
            <form id="plan_info" method="get" action="/plan/create">
               <div class="modal-body">
                  <input type="text" id="p_title" name="planTitle"
                     placeholder="여행제목" style="width: 308px"><br> <input
                     type="text" id="p_date" name="plan_date" placeholder="여행날짜"
                     style="width: 100%" readonly /> <input type="hidden"
                     name="total_date" id="t_date" /> <input type="hidden"
                     name="info" value="test">
               </div>
            </form>
            <div class="modal-footer">
               <button class="btn btn-primary" type="button" id="confirm">예</button>
               <button class="btn btn-primary" type="button" data-dismiss="modal">아니요</button>
            </div>
         </div>
      </div>
   </div>
</body>
</html>