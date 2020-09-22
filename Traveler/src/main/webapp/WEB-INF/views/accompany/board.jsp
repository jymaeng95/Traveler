<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../includes/sidebar_setting.jsp" %>
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
}
section.notice {
  padding: 80px 0;
}

.page-title {
  margin-bottom: 60px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}

#board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}
#board-search .search-window .search-wrap {
  position: relative;
  padding-right: 124px;
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}
#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}
#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}
#board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}
.board-table a:hover {
  text-decoration: underline;
}
.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 100px;
  text-align: center;
}

.board-table .th-date {
  width: 200px;
}

.board-table th, .board-table td {
  padding: 14px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover, .btn-dark:focus, #searchDate:hover, #searchDate:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* reset */

* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.clearfix:after {
  content: '';
  display: block;
  clear: both;
}
.container {
  width: 1100px;
  margin: 0 auto;
}
.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}
#searchDate {
  float:right;
  position:absolute;   
  background: #555;
  color: #fff; 
  margin-left:135px;  
  display: inline-block;
  padding: 8.6px 15px 8.6px;
  font-size: 15px;
  font-weight: 400;
  text-align: center;
  border: 0;
  outline: 0;
}


</style>
</head>
<script>
$(document).ready(function(){
	formatDate();
	
	$(".board-table tbody tr").click(function(){ 
		var tr = $(this);
	    var td = tr.children();
		var contentno = td.eq(2).text();
		var planno = td.eq(1).text();
		var writer = td.eq(0).text();
		
		location.href="/accompany/board_detail?contentId="+contentno+"&planNo="+planno+"&writer="+writer;
	});

	//paging
	
	var actionForm = $("#actionForm");
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		console.log('click');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	 });

	 var searchForm = $("searchForm");
	 $("searchForm button").on("click", function(e){
		if(!searchForm.find("option:selected").val()){
			alert("검색종류를 입력하세요");
			return false;

		}
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();
		searchForm.submit();
	});
});

//날짜 변환 함수
function formatDate(){
	var curDate = $("[id^=date]");
	curDate.each(function(i) {
		var cdate = $("#date"+i).html();
		var today, resultDate;
		today = new Date();
		resultDate = new Date(cdate);
		timegap = (today - resultDate)/(60*60*1000);
		
		var curYear = resultDate.getFullYear();
		var curMonth = (resultDate.getMonth() + 1);
		var curDay = resultDate.getDate();
	
		if (timegap <= 24) {
			if (Math.floor(timegap) == 0) {
				resultDate = Math.floor(timegap * 24) + '분 전';
			}
			else {
				resultDate = Math.floor(timegap) + '시간 전';
			}
		}
		else {
			resultDate = curYear + '-' + curMonth + '-' + curDay;
		}
		$("#date"+i).html(resultDate);
		
	});
}


</script>
<body>
<%@include file="../includes/sidebar.jsp"%>
<main class="page-content">
<section class="notice">
  <div class="page-title">
        <div class="container">
            <a href="/accompany/board"><h3>모집 게시판</h3></a>
        </div>
    </div>

    <!-- board seach area -->
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form id="searchForm" action="/accompany/board" method='get'>
                    <div class="search-wrap">
                        <label for="search" class="blind"></label>
                        <select name='type' style="float:left;position:absolute; margin: 10px -80px 10px;">
			  				<option value="T"
			  				<c:out value="${pageMaker.cri.type eq 'T'?'selected' :'' }"/>>제목</option>
			  				<option value="C"
			  				<c:out value="${pageMaker.cri.type eq 'C'?'selected' :'' }"/>>내용</option>
			  				<option value="W"
			  				<c:out value="${pageMaker.cri.type eq 'W'?'selected' :'' }"/>>작성자</option>
                        </select>
		 	  			<input type='search' name='keyword' id="search" placeholder="검색어를 입력해주세요."
			  			value='<c:out value="${pageMaker.cri.keyword }"/>'/>
			  			<input type='hidden' name='pageNum' 
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
                    <th scope="col" class="th-num">번호</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-date">등록일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${board}" var="list" varStatus="status">
		      	<tr>
			      	<td style="display:none;">${list.userId }</td>
			      	<td style="display:none;">${list.planNo }</td>
			        <td>${list.bno }</td>
			        <th id="trlink"><a href="#!">${list.title }</a></th>
			        <td id="date${status.index }">${list.writeDate }</td>
		      	</tr>
		      	</c:forEach>
                </tbody>
            </table>
            <c:if test="${count eq 0 }">
            	<p style="text-align:center;margin-top:30px;">검색 결과가 없습니다.</p>
            </c:if>
        </div>
    </div>

</section>
	  
	  <!-- pagination -->
	    <div class="text-center" style="margin:-40px 0 0 540px;">
					<div class="col-sm-12">
						<ul class="pagination" id="pagination-demo">
							<c:if test="${pageMaker.prev }">
								<li class="paginate_button previous" style="padding-right: 5px;"><a
									href="${pageMaker.startPage -1 }">&laquo;</a></li>
							</c:if>
							<c:forEach var="num" begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }">
								<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }"
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
	    	<form id='actionForm' action="/accompany/board" method='get'>
	    		<input type='hidden' name='pageNum' value= '${pageMaker.cri.pageNum }'>
	    		<input type='hidden' name='type' value= '${pageMaker.cri.type }'>
	    		<input type='hidden' name='keyword' value= '${pageMaker.cri.keyword }'>
	    	</form>
	    </div>

    	<!-- end pagination -->
</main>
<script src="/resources/index/js/holder.js"></script>
</body>
</html>