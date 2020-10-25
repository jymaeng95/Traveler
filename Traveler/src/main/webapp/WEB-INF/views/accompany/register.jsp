<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>
<link href="/resources/accompany/css/register.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,700" rel="stylesheet">
<script src="/resources/accompany/js/register.js"></script>
<title>Insert title here</title>
</head>
<body>
   <%@include file="../includes/sidebar.jsp"%>
   <main class="page-content">
      <div class="container">
         <h2 style="margin-left:5%;"><c:out value="${date[0]}"></c:out></h2>
            <c:set var="listDate" value="${date[0]}"></c:set>
         <c:forEach items="${accPlan}" var="list" varStatus="status">
       <c:if test="${list.isacc eq 'N' }">
         <c:if test="${date[status.index] ne listDate }">
            <h2 style="margin-left:5%;"><c:out value="${date[status.index] }"></c:out></h2>
            <c:set var="listDate" value="${date[status.index]}"></c:set>
         </c:if>
         <div class="blog-card">
            <div class="meta">
               <div class="photo"
                  style="background-image: url('${list.img_src}')"></div>
               <ul class="details">
                  <li class="author"><a href="#">John Doe</a></li>
                  <li class="date">Aug. 24, 2015</li>
                  <li class="tags">
                     <ul>
                        <li><a href="#">Learn</a></li>
                        <li><a href="#">Code</a></li>
                        <li><a href="#">HTML</a></li>
                        <li><a href="#">CSS</a></li>
                     </ul>
                  </li>
               </ul>
            </div>
            
            <div class="description">
               <h1 class="title"><c:out value="${list.title }"></c:out></h1>
               <h2 class="acc-time"><c:out value="${time[status.index] }"></c:out></h2>
               <p><c:out value="${list.overview }"></c:out></p>
               <input type="hidden" name = "acc-startDate" value = "${list.startDate }">
               <button type="button" class="btn btn-link btn-acc"  onclick=""
                        style="color: #5ad67d; margin-top:20px; float:right;" value="${list.planNo }">동행 구하기</button>
            </div>
         </div>
         </c:if>
         </c:forEach>
      </div>
   </main>
   </div>
   <div id="modal-register" style="height: 100%; display: none;"
      class="modal fade bs-example-modal-lg in" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog modal-lg" style="height: 98%">
         <div class="modal-content" style="height: 95%; border-radius: 0px;">
            <div class="modal-header">
               <h4 class="modal-title" id="title-spot"></h4>
               <button type="button" class="close" data-dismiss="modal"
                  aria-hidden="true">×</button>
            </div>
            <div class="modal-body" style="height: 94%; padding: 0px;">
               <iframe id="if_guidebook" frameborder="0" width="100%" name="detail"
                  height="100%" src="/accompany/register/detail"></iframe>
            </div>
         </div>
      </div>
   </div>
</body>
</html>