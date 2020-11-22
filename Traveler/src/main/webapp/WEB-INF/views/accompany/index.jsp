<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/accompany/css/index.css" rel="stylesheet">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/accompany/js/index.js"></script>
<style>
.pagination {
  display: inline-block;
}

.pagination li {
  color: black;
  float: left;
  padding: 6px 12px;
  text-decoration: none;
  border: 1px solid #ddd;
}

.pagination a {
  color: black;
  font-size: 12px;
}

.pagination li.active {
  background-color: #c8c8c8;
  color: white;
  border: 1px solid #c8c8c8;
}

.pagination li:hover:not(.active) {background-color: #ddd;}

.pagination li:first-child {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}

.pagination li:last-child {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>
</head>
<body>
   <%@include file="../includes/sidebar.jsp"%>
   <main class="page-content">
      <div class="container">
         <section class="bg-light" style="margin-top: 15px;">
            <div class="container"
               style="overflow-x: scroll; padding-top: 0px; margin-top: 0px; margin-bottom: 10px;">
               <h2 class="pt-3 pb-3"
                  style="padding-left: 20px; font-size: 18pt; font-weight: 300; color: #696969">
               </h2>
               <!-- c태그로 if문으로 제어  -->
               <c:if test="${userInfo.userId == null }">
                  <div
                     style="text-align: center; padding-top: 40px; padding-bottom: 40px; color: #c0c0c0; font-size: 12pt">
                     새로운 여행을 만들어 보세요!</div>
               </c:if>
               <c:if test="${userInfo.userId != null }">
                  <c:if test="${myacc.size() == 0 && myguestacc.size() == 0 }">
                     <div
                        style="text-align: center; padding-top: 40px; padding-bottom: 40px; color: #c0c0c0; font-size: 12pt">
                        새로운 동행을 만들어 보세요!</div>
                  </c:if>
                  <c:if test="${myacc.size() != 0 || myguestacc.size() != 0 }">


                     <div class="scrolling-wrapper-flexbox">
                        <c:forEach items="${myacc }" var="list">
                           <div class="accompany accompany--big"
                              style="margin: 10px 30px 20px 30px;">
                              <div class="accompany__image">
                                 <img class="accompany_image" src="/resources/accompany/img/host.png"
                                    style="width: 100%; height: 100%"></img>
                              </div>
                              <h2 class="accompany__title">
                                 <c:out
                                    value="${list.boardTitle }"></c:out>
                              </h2>
                              <p class="accompany__info">
                                 <c:out value="여행지: ${list.title }"></c:out>
                                 <br>
                                 <c:out value="여행 날짜: ${list.startDate }"></c:out>
                                 <br>
                              </p>
                              <div class="accompany__action-bar">
                                 <input type="hidden" value="${list.planNo }"/>
                                 <input type="hidden" value="${list.title }"/>
                                 <input type="hidden" id="hostId"value="${list.hostId }"/>
                                 <input type="hidden" id="title" value="${list.title }"/>
                                 <input type="hidden" id="planNo" value="${list.planNo }"/>                                 
                                <button class="accompany__button btnRead"
                                    value="${list.accBno }"
                                    style="position: absolute; left: 40%;">Read</button>
                              </div>
                           </div>
                        </c:forEach>
                        <c:forEach items="${guestBoard }" var="list">
                           <div class="accompany accompany--big"
                              style="margin: 10px 30px 20px 30px;">
                              <div class="accompany__image">
                                 <img class="accompany_image" src="/resources/accompany/img/guest.png"
                                    style="width: 100%; height: 100%"></img>
                              </div>
                              <h2 class="accompany__title">
                                 <c:out
                                    value="${list.boardTitle }"></c:out>
                              </h2>
                              <p class="accompany__info">
                                 <c:out value="여행지: ${list.title }"></c:out>
                                 <br>
                                 <c:out value="여행 날짜: ${list.startDate }"></c:out>
                                 <br>
                              </p>
                              <div class="accompany__action-bar">
                                 <input type="hidden" value="${list.planNo }"/>
                                 <input type="hidden" value="${list.title }"/>
                                 <input type="hidden" id="hostId"value="${list.hostId }"/>
                                 <input type="hidden" id="title" value="${list.title }"/>
                                 <input type="hidden" id="planNo" value="${list.planNo }"/>                                 
                                <button class="accompany__button btnRead"
                                    value="${list.accBno }"
                                    style="position: absolute; left: 40%;">Read</button>
                              </div>
                           </div>
                        </c:forEach>
                     </div>
                     <!-- 읽기 / 수정 / 삭제  구현 위한것  -->
                     <form action="#" method="GET" id="modifyForm">
                        <input type="hidden" name="when" value="modify">
                     </form>
                     <form action="#" method="GET" id="readForm"></form>
                     <form action="#" method="GET" id="deleteForm"></form>
                  </c:if>

               </c:if>
            </div>
         </section>
      </div>
      <section class="notice">
         <div class="page-title">
            <div class="container">
               <a href="/accompany/index"><h3>모집 게시판</h3></a>
            </div>
         </div>

         <!-- board seach area -->
         <div id="board-search">
            <div class="container">
               <div class="search-window">
                  <form id="searchForm" action="/accompany/index" method='get'>
                     <div class="search-wrap">
                        <label for="search" class="blind"></label> <select name='type'
                           style="float: left; position: absolute; margin: 10px -100px 10px;">
                           <option value="T"
                              <c:out value="${pageMaker.cri.type eq 'T'?'selected' :'' }"/>>여행지</option>
                           <option value="C"
                              <c:out value="${pageMaker.cri.type eq 'C'?'selected' :'' }"/>>여행 제목</option>
                           <option value="W"
                              <c:out value="${pageMaker.cri.type eq 'W'?'selected' :'' }"/>>작성자</option>
                        </select> <input type='search' name='keyword' id="search"
                           placeholder="검색어를 입력해주세요."
                           value='<c:out value="${pageMaker.cri.keyword }"/>' /> <input
                           type='hidden' name='pageNum'
                           value='<c:out value="${pageMaker.cri.pageNum }"/>'>
                        <button class="btn btn-dark">검색</button>
                        <!--                         <button id="searchDate">날짜로 검색</button> -->
                     </div>
                  </form>
               </div>
            </div>
         </div>

         <!-- board list area -->
         <div id="board-list">
            <div class="container">
               <table class="board-table">
                  <thead>
                     <tr>
                        <th scope="col" class="th-boardtitle">여행 제목</th>
                        <th scope="col" class="th-title">여행지</th>
                        <th scope="col" class="th-num">인원 수</th>
                        <th scope="col" class="th-startdate">여행 날짜</th>
                        <th scope="col" class="th-date">등록일</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach items="${acc_board}" var="list" varStatus="status">
                        <tr>
                           <td style="display: none;">${list.accBno }</td>
                           <td style="display: none;">${list.planNo }</td>
                           <td style="display: none;">${list.hostId }</td>
                           <th id="trlink" data-toggle="tooltip" title="${list.boardTitle }" ><a href="#!">${list.boardTitle }</a></th>
                           <td data-toggle="tooltip" title="${list.title }" >${list.title }</td>
                           <td>${numlist[status.index].curPerson }/${numlist[status.index].limitPerson }</td>
                           <td>${list.startDate }</td>
                           <td id="date${status.index }">${list.regDate }</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
               <c:if test="${count eq 0 }">
                  <p style="text-align: center; margin-top: 30px;">검색 결과가 없습니다.</p>
               </c:if>
               <div class="row">
                  <div class="col-sm-7">
                     <ul class="pagination" id="pagination-demo">
                        <c:if test="${pageMaker.prev }">
                           <li class="paginate_button previous"
                              style="padding-right: 5px;"><a
                              href="${pageMaker.startPage -1 }">&laquo;</a></li>
                        </c:if>
                        <c:forEach var="num" begin="${pageMaker.startPage }"
                           end="${pageMaker.endPage }">
                           <li class="paginate_button ${pageMaker.cri.pageNum == num ? "
                              active":"" }"
                        style="padding-right: 5px;"><a
                              href="${num }">${num} </a></li>
                        </c:forEach>
                        <c:if test="${pageMaker.next }">
                           <li class="paginate_button next"><a
                              href="${pageMaker.endPage +1 }">&raquo;</a></li>
                        </c:if>
                     </ul>
                  </div>
                  <form id='actionForm' action="/accompany/index" method='get'>
                     <input type='hidden' name='pageNum'
                        value='${pageMaker.cri.pageNum }'> <input type='hidden'
                        name='type' value='${pageMaker.cri.type }'> <input
                        type='hidden' name='keyword' value='${pageMaker.cri.keyword }'>
                  </form>

                  <div class="col-sm-5">
                     <!-- 자신의 계획 없는 경우 계획 만들러가는 버튼 생성  -->
                     <c:if test="${empty accPlanner}">
                        <button type="button" class="btn btn-dark" id="btn-mybudget"
                           style="float: right; margin-top: 2%;"
                           onclick="location.href='/plan/plan'">새로운 계획 만들기</button>
                     </c:if>
                     <!-- 자신의 계획 있는 경우 동행 구하는 버튼 생성  -->
                     <c:if test="${not empty accPlanner}">
                     <button type="button" class="btn btn-dark" id="btn-register"
                        style="float: right; margin-top: 2%; margin-right: 2%;">동행
                        구하기</button>
                     </c:if>
                  </div>
               </div>
            </div>
         </div>
      </section>


      <!-- end pagination -->
   </main>
   <div class="modal fade" tabindex="-1" role="dialog"
      aria-labelledby="mySmallModalLabel" id="modal" aria-hidden="true">
      <div class="modal-dialog"
         style="max-width: 100%; width: auto; display: table;">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="myModalLabel">새로운 동행 구하기</h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">×</span>
               </button>

            </div>
            <div class="modal-body">
               <h5>동행 구할 여행 제목 선택</h5>
               <select id="planTitle" name="planTitle" style="width:100%;" required autofocus>
                  <c:forEach items="${accPlanner }" var="list" varStatus="status">
                     <option value="${list.planNo }"><c:out
                           value="${list.planTitle }"></c:out></option>
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
   <script src="/resources/index/js/holder.js"></script>
</body>
</html>