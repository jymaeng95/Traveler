<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>
<link href="/resources/spot/css/spot.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,700"
	rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<script src="/resources/spot/js/spot.js"></script>
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
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<input type="hidden" id="pageMaker" value="${pageMaker }">
	<input type="hidden" id="sigunguCode" value="${sigunguCode }">
	<input type="hidden" id="contentTypeId" value="${contentTypeId }">
	<main class="page-content">
		<div class="container">
			<h1 style="margin: 1rem auto; padding-left: 5%;" class="page-header">
				<small>관광정보(<span class="totalCount"></span>개)
				</small>
			</h1>
			<hr style="width: 90%">
			<div class="row"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<small style="font-size: 1.5em;">&nbsp;&nbsp;관광지:&nbsp;&nbsp;</small>
				<input type="checkbox" name="sigungu" id="all" value="" style=""
					onclick="oneCheckLocation(this);" /><span class="category-text">전체</span>
				<input type="checkbox" name="sigungu" id="seoguipo" value="3"
					onclick="oneChecLocation(this);" /><span class="category-text">서귀포</span>
				<input type="checkbox" name="sigungu" id="jeju" value="4"
					onclick="oneCheckLocation(this);" /><span class="category-text">제주</span>
			</div>
			<hr style="width: 90%">
			<div class="row"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<small style="font-size: 1.5em;">컨텐츠:&nbsp;&nbsp;</small> <input
					type="checkbox" name="contentType" id="all_contents" value=""
					onclick="oneCheckContents(this);" /><span class="category-text">전체</span><input
					type="checkbox" name="contentType" id="travel" value="12"
					onclick="oneCheckContents(this);" /><span class="category-text">관광</span><input
					type="checkbox" name="contentType" id="culture" value="14"
					onclick="oneCheckContents(this);" /><span class="category-text">문화시설</span>
				<input type="checkbox" name="contentType" id="events" value="15"
					onclick="oneCheckContents(this);" /><span class="category-text">행사</span><input
					type="checkbox" name="contentType" id="cose" value="25"
					onclick="oneCheckContents(this);" /><span class="category-text">여행코스</span>
				<input type="checkbox" name="contentType" id="leisure" value="28"
					onclick="oneCheckContents(this);" /><span class="category-text">레포츠</span>
				<input type="checkbox" name="contentType" id="hotel" value="32"
					onclick="oneCheckContents(this);" /><span class="category-text">숙박</span>
				<input type="checkbox" name="contentType" id="shopping" value="38"
					onclick="oneCheckContents(this);" /><span class="category-text">쇼핑</span><input
					type="checkbox" name="contentType" id="food" value="39"
					onclick="oneCheckContents(this);" /><span class="category-text">음식</span>
				<form action="/spot/spot" method="GET" name="srchForm">
					<input type="hidden" name="sigunguCode" id="s_Code" value="" /> <input
						type="hidden" name="contentTypeId" id="ctype_id" value="" />
					<button type="button" id="searchBtn" class="btn btn-primary btn-sm"
						onclick="searchClick();" style="float: right">검색</button>
				</form>
			</div>
			<hr style="width: 90%">
			<form method="GET" name="imgForm" id="imgForm">
				<c:forEach var="i" begin="0" end="9">
					<div class="blog-card">
						<div class="meta">
							<div class="photo" id="photo${i }"
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
							<h1 id="title${i }"></h1>
							<h2 id="addr${ i}"></h2>
							<p id="overview${i }"></p>


							<button type="button" class="btn btn-link" id="btn-detail${i }"
								style="color: #5ad67d">Read More</button>

						</div>
					</div>
				</c:forEach>
			</form>
			<div class="row text-center"
				style="margin: 1rem auto; padding-right: 5%; padding-left: 5%">
				<div class="col-sm-12">
					<ul class="pagination" id="pagination-demo">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1 }">&laquo;</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.pageNum == num ? "active":"" }">
								<a href="${num }">${num} </a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>

				<form id='actionForm' action="/spot/spot" method='get'>
					<input type='hidden' name='pageNum' value='${pageMaker.pageNum }'>
					<input type="hidden" name="sigunguCode" id="sigu"
						value="${sigunguCode }" /> <input type="hidden"
						name="contentTypeId" id="cont" value="${contentTypeId }" />
				</form>

			</div>
		</div>
	</main>
	</div>
</body>
</html>