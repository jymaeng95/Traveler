<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/resources/plan/css/sidebar.css">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	rel="stylesheet">
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

	<!-- 테스트 부트스트랩 -->
	<div class="page-wrapper chiller-theme toggled">
		<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
			class="fas fa-bars"></i>
		</a>
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="sidebar-brand">
					<a href="#">관광지 추천</a> <a href="#">북마크</a>
				</div>
				<!-- sidebar-header  -->
				<div class="sidebar-search">
					<div>
						<div class="input-group">
							<input type="text" class="form-control search-menu"
								placeholder="검색...">
							<div class="input-group-append">
								<span class="input-group-text"> <i class="fa fa-search"
									aria-hidden="true"></i>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!-- sidebar-search  -->
				<div class="sidebar-menu">
					<ul>

						<%@include file="../plan/category/recommend.jsp"%>

					</ul>
				</div>
				<!-- sidebar-menu  -->
			</div>
			<!-- sidebar-content  -->
			<div class="sidebar-footer">
				<form action="/plan/my_plan2" method="GET">
					<button type="submit" class="btn-test" type="reset">버튼</button>
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