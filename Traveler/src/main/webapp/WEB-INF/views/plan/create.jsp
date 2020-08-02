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
<script src="/resources/plan/js/plan.js"></script>
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
						style="color: #f9f9f9;">관광지 추천</button>
					<c:choose>
						<c:when test="${userInfo.userId != null }">
							<button type="button" id="bookmark" class="btn btn-link"
								style="color: #f9f9f9;">북마크</button>
							<button type="button" id="myplan" class="btn btn-link"
								style="color: #f9f9f9;">내 여행</button>
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
							<input type="text" class="form-control search-menu"
								placeholder="검색..." style="background: #f9f9f9;">
							<div class="input-group-append">
								<span class="input-group-text" style="background: #e9e9e9;">
									<i class="fa fa-search" aria-hidden="true"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!-- sidebar-search  -->
				<div class="sidebar-menu" style="background: #f5f5f5;">
					<ul id="ul-recommend" style="padding:10px 20px;">
						<%@include file="../plan/category/recommend.jsp"%>
					</ul>
					<ul id="ul-bookmark" style="display: none; padding:10px 20px">
						<%@include file="../plan/category/bookmark.jsp"%>
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
					<button type="submit" class="btn btn-primary" type="reset">버튼</button>
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

</body>
</html>