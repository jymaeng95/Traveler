
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
   href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
   rel="stylesheet">
<link href="/resources/accompany/css/register-modal.css"
   rel="stylesheet">
<link href="/resources/util/css/font.css" rel="stylesheet">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.js"></script>
<script src="/resources/accompany/js/register-modal.js"></script>
<script src="https://kit.fontawesome.com/27324e8e3c.js"
   crossorigin="anonymous"></script>

</head>
<body>

   <c:if test="${fn:length(accRandRecommend) != 0}">
      <table class="table"
         style="margin-bottom: 50px; background: gainsboro;">
         <!-- c태그로 DB에서 추천 가져와서 하기 -->
         <thead class="thead-dark">
            <tr>
               <th scope="col">HostId</th>
               <th scope="col">Board_title</th>
               <th scope="col">Participant</th>
               <th scope="col">date</th>
               <th></th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${accRandRecommend }" var="accList"
               varStatus="status">
               <input type="hidden" name="accPlanNo" value="${accList.PLANNO }" >
               <input type="hidden" name="accTitle" value="${accList.TITLE }" >
               <input type="hidden" name="accBno" value="${accList.ACC_BNO }" >
               <input type="hidden" name="accHostId" value="${accList.HOSTID }" >
               <tr title="${accList.ACC_DESCRIPT }">
                  <th scope="row"><c:out value="${accList.HOSTID }"></c:out></th>
                  <td><c:out value="${accList.BOARD_TITLE }"></c:out></td>
                  <td><c:out
                        value="${accList.CUR_PERSON } / ${accList.LIMIT_PERSON }"></c:out></td>
                  <td><c:out value="${accList.STARTDATE }"></c:out></td>
                  <td class="row-options"><i class="fas fa-search-plus" onclick="checkForm();"></i></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
   </c:if>

   <!-- 신청서 작성하기  -->
   <form id="contact" action="/accompany/recruit" method="post"
      name="content_form">
      <h3>${title }새로운 동행</h3>

      <fieldset>
         <input placeholder="동행 제목을 적어주세요" type="text" tabindex="1"
            name="boardTitle" required>
      </fieldset>
      <fieldset>
         <select id="limitPerson" name="limitPerson" tabindex="2" required
            autofocus>
            <option value="" disabled selected>동행 신청 인원을 선택해주세요.</option>
            <c:forEach var="i" begin="1" end="10">
               <option value="${i }"><c:out value="${i}"></c:out></option>
            </c:forEach>
         </select>
      </fieldset>

      <fieldset>
         <textarea placeholder="동행 소개를 적어주세요." tabindex="3" name="descript"
            required></textarea>
      </fieldset>
      <fieldset>
         <button name="submit" type="submit" id="contact-submit"
            data-submit="...Sending" onclick="checkForm();">Submit</button>
      </fieldset>
      <input type="hidden" name="planNo" value="${planNo }"> <input
         type="hidden" name="title" value="${title }"> <input
         type="hidden" name="startDate" value="${startDate }">
   </form>
</body>
</html>