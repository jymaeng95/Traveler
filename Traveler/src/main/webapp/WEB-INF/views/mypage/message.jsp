<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<%@ include file="../includes/sidebar_setting.jsp" %>
	<link href="/resources/mypage/css/message.css" rel="stylesheet">
	<script src="/resources/mypage/js/message.js"></script>
  </head>
  <body>
  <%@include file="../includes/sidebar.jsp"%>
   <!-- sidebar-wrapper  -->
   <main class="page-content">
   <h1>쪽지</h1><hr/><br/>
<tabtable>
  
  <input id="tab01" class="tab ${tabPage == rcv_list ? "active":"" }" type="radio" name="tabs" <c:if test="${tabPage eq 'rcv_list'}">checked</c:if> onclick="return(false);">
  <label for="tab01" class="tab"><a href="rcv_list" style="color: black; text-decoration: none;">받은 쪽지함</a></label>  
  
  <input id="tab02" class="tab ${tabPage == send_list ? "active":"" }" type="radio" name="tabs" <c:if test="${tabPage eq 'send_list'}">checked</c:if> onclick="return(false);">
  <label for="tab02" class="tab"><a href="send_list" style="color: black; text-decoration: none;">보낸 쪽지함</a></label>
    
  <input id="tab03" class="tab ${tabPage == archive ? "active":"" }" type="radio" name="tabs" <c:if test="${tabPage eq 'archive'}">checked</c:if> onclick="return(false);">
  <label for="tab03" class="tab"><a href="archive" style="color: black; text-decoration: none;">보관함</a></label>
  
  
<!-- 받은 쪽지함 탭 -->
  <tabsection id="content1">
   <div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2>받은 <b>쪽지함</b></h2><input id="name" name="name" style="display:none">
        </div>
        <div class="col-sm-6">
          <button class="btn btn-success" onclick="sendpop()"><i class="fas fa-plus"></i><span>쪽지 보내기</span></button>
          <button class="btn btn-danger" onclick="delselect()"><i class="fas fa-trash-alt"></i><span>선택 삭제</span></button>
        </div>
      </div>
    </div>
    
    <table class="table table-striped table-hover" style="table-layout:fixed" id="msgtable">
      <thead>
        <tr>
          <th>
            <span>
              <input type="checkbox" id="selectAll" onclick="selectAll();"><label for="selectAll"></label>
            </span>
          </th>	
          <th>보낸 사람</th>
          <th>내용</th>
          <th>보낸 날짜</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${msg_list}" var="list" varStatus="status">
        <tr>
          <td>
              <input type="checkbox" id="options" name="options">
          </td>
          <td style="display:none">${list.mid }</td><td style="display:none">${userInfo.userId }</td>
          <td>${list.sender}</td>
          <td style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap;" onclick="updateMsg2('${userInfo.userId}','${list.mid}','1');popup('${list.mid }', 'rcv');">${list.mcontent}</td>
          <td><p id="date${status.index }">${list.senddate }</p></td>
          <td style="text-align: right;"><c:if test="${list.readstatus eq '1'}"><i class="fas fa-check-circle" title="read"></i></c:if>
            <c:choose><c:when test="${list.status eq '0'}"><a onclick="updateMsg('${userInfo.userId}','${list.mid}','1');" class="edit" type="button">
            <i class="far fa-star" data-toggle="tooltip" title="Store"></i></a></c:when>
            <c:otherwise><a onclick="updateMsg('${userInfo.userId}','${list.mid}','0');" class="edit" type="button"><i class="fas fa-star" data-toggle="tooltip" title="Store"></i></a></c:otherwise></c:choose>
            <a href="#deleteModal" class="delete" data-toggle="modal" data-title="${list.mid }"><i class="fas fa-trash-alt" data-toggle="tooltip" title="Delete"></i></a>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>	
    <!-- pagination -->
    <div class="row text-center"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<div class="col-sm-12">
					<ul class="pagination" id="pagination-demo">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous" style="padding-right: 5px;"><a
								href="${pageMaker.startPage -1 }">&laquo;</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.pageNum == num ? "active":"" }"
							style="padding-right: 5px;">
								<a href="${num }">${num} </a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
    	<form id='actionForm' action="/mypage/message" method='get'>
    		<input type='hidden' name='pageNum' value= '${pageMaker.pageNum }'>
    		<input type='hidden' name='tabPage' value= '${tabPage }'>
    	</form>
    </div>
    <!-- end pagination -->
  </div>
</div>
  </tabsection>
  
  
    <!-- 보낸 쪽지함 -->
  <tabsection id="content2">
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2>보낸 <b>쪽지함</b></h2><input id="name" name="name" style="display:none">
        </div>
        <div class="col-sm-6">
          <button class="btn btn-success" onclick="sendpop()"><i class="fas fa-plus"></i><span>쪽지 보내기</span></button>
          <button class="btn btn-danger" onclick="delselect()"><i class="fas fa-trash-alt"></i><span>선택 삭제</span></button>
        </div>
      </div>
    </div>
    
    <table class="table table-striped table-hover" style="table-layout:fixed">
      <thead>
        <tr>
          <th>
            <span>
              <input type="checkbox" id="selectAll1" onclick="selectAll();"><label for="selectAll"></label>
            </span>
          </th>
          <th>받는 사람</th>
          <th>내용</th>
          <th>보낸 날짜</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${msg_list2}" var="list" varStatus="status">
        <tr>
          <td>
              <input type="checkbox" id="options1" name="options1">
          </td>
          <td style="display:none">${list.mid }</td>
          <td>${list.userId}<input id="parentId" value="${list.userId}" style="display:none"></td>
          <td style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap;" onclick="popup('${list.mid}', 'send');">${list.mcontent}</td>
          <td><p id="date${status.index }">${list.senddate }</p></td>
          <td style="text-align: right;"><c:if test="${list.readstatus eq '1'}"><i class="fas fa-check-circle" title="read"></i></c:if>
            <c:choose><c:when test="${list.status eq '0'}"><a onclick="updateMsg('${list.userId}','${list.mid}','1');" class="edit" type="button">
            <i class="far fa-star" data-toggle="tooltip" title="Store"></i></a></c:when>
            <c:otherwise><a onclick="updateMsg('${list.userId}','${list.mid}','0');" class="edit" type="button"><i class="fas fa-star" data-toggle="tooltip" title="Store"></i></a></c:otherwise></c:choose>
            <a href="#deleteModal" class="delete" data-toggle="modal" data-title="${list.mid }"><i class="fas fa-trash-alt" data-toggle="tooltip" title="Delete"></i></a>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    
    <!-- pagination -->
    <div class="row text-center"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<div class="col-sm-12">
					<ul class="pagination" id="pagination-demo">
						<c:if test="${pageMaker2.prev }">
							<li class="paginate_button previous" style="padding-right: 5px;"><a
								href="${pageMaker2.startPage -1 }">&laquo;</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker2.startPage }"
							end="${pageMaker2.endPage }">
							<li class="paginate_button ${pageMaker2.pageNum == num ? "active":"" }"
							style="padding-right: 5px;">
								<a href="${num }">${num} </a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker2.next }">
							<li class="paginate_button next"><a
								href="${pageMaker2.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
    	<form id='actionForm' action="/mypage/message" method='get'>
    		<input type='hidden' name='tabPage' value= '${tabPage }'>
    		<input type='hidden' name='pageNum' value= '${pageMaker2.pageNum }'>
    	</form>
    </div>
    
    <!-- end pagination -->
    </div>
   </div>
   
 </tabsection>
    
    <!-- 보관함 -->
  <tabsection id="content3">
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2><b>보관함</b></h2><input id="name" name="name" style="display:none">
        </div>
        <div class="col-sm-6">
          <button class="btn btn-success" onclick="sendpop()" style="float:right;"><i class="fas fa-plus"></i><span>쪽지 보내기</span></button>
          <button class="btn btn-danger" onclick="delselect()" style="float:right;"><i class="fas fa-trash-alt"></i><span>선택 삭제</span></button>
          <select id="valarchive" style="float:right; margin-top:5px; margin-right:5px;">
          	<option value="rcv_arc" class="archive_select ${archive == rcv_arc ? "active":"" }"
          	 <c:if test="${arcPage eq 'rcv_arc'}">selected</c:if>>받은 쪽지</option>
          	<option value="send_arc" class="archive_select ${archive == send_arc ? "active":"" }"
          	<c:if test="${arcPage eq 'send_arc'}">selected</c:if>>보낸 쪽지</option>
    	  </select>
        </div>
      </div>
    </div>
    
    <table class="table table-striped table-hover" style="table-layout:fixed">
      <thead>
        <tr>
          <th>
            <span>
              <input type="checkbox" id="selectAll3" onclick="selectAll();"><label for="selectAll"></label>
            </span>
          </th>
          <th><c:if test="${arcPage eq 'rcv_arc'}">보낸 사람</c:if>
          <c:if test="${arcPage eq 'send_arc'}">받는 사람</c:if></th>
          <th>내용</th>
          <th>보낸 날짜</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${msg_list3}" var="list" varStatus="status">
        <tr>
          <td>
              <input type="checkbox" id="options4" name="options4">
          </td>
          <td style="display:none">${list.mid }</td><td style="display:none">${userInfo.userId }</td>
          <td>${list.sender}</td>
          <td style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap;" onclick="updateMsg2('${userInfo.userId}','${list.mid}','1');popup('${list.mid }', 'rcv');">${list.mcontent}</td>
          <td><p id="date${status.index }">${list.senddate }</p></td>
          <td style="text-align: right;"><c:if test="${list.readstatus eq '1'}"><i class="fas fa-check-circle" title="read"></i></c:if>
            <c:choose><c:when test="${list.status eq '0'}"><a onclick="updateMsg('${userInfo.userId}','${list.mid}','1');" class="edit" type="button">
            <i class="far fa-star" data-toggle="tooltip" title="Store"></i></a></c:when>
            <c:otherwise><a onclick="updateMsg('${userInfo.userId}','${list.mid}','0');" class="edit" type="button"><i class="fas fa-star" data-toggle="tooltip" title="Store"></i></a></c:otherwise></c:choose>
            <a href="#deleteModal" class="delete" data-toggle="modal" data-title="${list.mid }"><i class="fas fa-trash-alt" data-toggle="tooltip" title="Delete"></i></a>
          </td>
        </tr>
        </c:forEach>
        <c:forEach items="${msg_list4}" var="list" varStatus="status">
        <tr>
          <td>
              <input type="checkbox" id="options4" name="options4">
          </td>
          <td style="display:none">${list.mid }</td>
          <td>${list.userId}</td>
          <td style="text-overflow:ellipsis; overflow:hidden; white-space:nowrap;"  onclick="popup('${list.mid}', 'send');">${list.mcontent}</td>
          <td><p id="date${status.index }">${list.senddate }</p></td>
          <td style="text-align: right;"><c:if test="${list.readstatus eq '1'}"><i class="fas fa-check-circle" title="read"></i></c:if>
            <c:choose><c:when test="${list.status eq '0'}"><a onclick="updateMsg('${list.userId}','${list.mid}','1');" class="edit" type="button">
            <i class="far fa-star" data-toggle="tooltip" title="Store"></i></a></c:when>
            <c:otherwise><a onclick="updateMsg('${list.userId}','${list.mid}','0');" class="edit" type="button"><i class="fas fa-star" data-toggle="tooltip" title="Store"></i></a></c:otherwise></c:choose>
            <a href="#deleteModal" class="delete" data-toggle="modal" data-title="${list.mid }"><i class="fas fa-trash-alt" data-toggle="tooltip" title="Delete"></i></a>
          </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    <!-- pagination -->
    <c:if test="${arcPage eq 'rcv_arc' }">
        <div class="row text-center"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<div class="col-sm-12">
					<ul class="pagination" id="pagination-demo">
						<c:if test="${pageMaker3.prev }">
							<li class="paginate_button previous" style="padding-right: 5px;"><a
								href="${pageMaker3.startPage -1 }">&laquo;</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker3.startPage }"
							end="${pageMaker3.endPage }">
							<li class="paginate_button ${pageMaker3.pageNum == num ? "active":"" }"
							style="padding-right: 5px;">
								<a href="${num }">${num} </a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker3.next }">
							<li class="paginate_button next"><a
								href="${pageMaker3.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
    	<form id='actionForm' action="/mypage/message" method='get'>
    		<input type='hidden' name='pageNum' value= '${pageMaker3.pageNum }'>
    		<input type='hidden' name='tabPage' value= '${tabPage }'>
    	</form>
    </div></c:if>
    <c:if test="${arcPage eq 'send_arc' }">
        <div class="row text-center"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<div class="col-sm-12">
					<ul class="pagination" id="pagination-demo">
						<c:if test="${pageMaker4.prev }">
							<li class="paginate_button previous" style="padding-right: 5px;"><a
								href="${pageMaker4.startPage -1 }">&laquo;</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker4.startPage }"
							end="${pageMaker4.endPage }">
							<li class="paginate_button ${pageMaker4.pageNum == num ? "active":"" }"
							style="padding-right: 5px;">
								<a href="${num }">${num} </a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker4.next }">
							<li class="paginate_button next"><a
								href="${pageMaker4.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
    	<form id='actionForm' action="/mypage/message" method='get'>
    		<input type='hidden' name='pageNum' value= '${pageMaker4.pageNum }'>
    		<input type='hidden' name='tabPage' value= '${tabPage }'>
    	</form>
    </div></c:if>
    <!-- end pagination -->
  </div>
</div>
  </tabsection>

 <!-- 삭제 모달 -->
<div id="deleteModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form>
        <div class="modal-header">						
          <h4 class="modal-title">쪽지 삭제</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">					
          <p>쪽지를 삭제하시겠습니까?</p><h4 class="modal-title" id="myModalLabel"/>
          <p><small>다시 복원 할 수 없습니다.</small></p>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="button" class="btn btn-danger" value="Delete" onClick="delMsg('${userInfo.userId}');">    
        </div>
      </form>
    </div>
  </div>
</div>
 
</tabtable>
   </main>
   <script src="/resources/index/js/holder.js"></script>
  </body>
</html>