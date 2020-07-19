<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>
<link href="/resources/mypage/css/bookmark.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,700" rel="stylesheet">
<script src="/resources/mypage/js/bookmark.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container">
			<c:forEach items="${b_list }" var="list" varStatus="status">
			<c:if test="${status.index%2==0 }">
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
					<h1><c:out value="${list.title }"></c:out></h1>
					<h2><c:out value="${list.addr }"></c:out></h2>
					<p><c:out value="${list.overview }"></c:out></p>
					<button type="button" class="btn btn-link" id="delete-bookmark" onclick="deleteBookmark('${userInfo.userId}','${list.contentId }');"
								style="color: #5ad67d; margin-top:20px; float:right;">삭제</button>
					<button type="button" class="btn btn-link" id="btn-detail" onclick="readMoreClick('${list.contentId}','${list.contentTypeId }');"
								style="color: #5ad67d; margin-top:20px; float:right;">Read More</button>
				</div>
			</div>
			</c:if>
			<c:if test="${status.index%2!=0 }">
			<div class="blog-card alt">
				<div class="meta">
					<div class="photo"
						style="background-image: url('${list.img_src}')"></div>
					<ul class="details">
						<li class="author"><a href="#">Jane Doe</a></li>
						<li class="date">July. 15, 2015</li>
						<li class="tags">
							<ul>
								<li><a href="#">Learn</a></li>
								<li><a href="#">Code</a></li>
								<li><a href="#">JavaScript</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="description">
					<h1><c:out value="${list.title }"></c:out></h1>
					<h2><c:out value="${list.addr }"></c:out></h2>
					<p><c:out value="${list.overview }"></c:out></p>
					<button type="button" class="btn btn-link" id="btn-detail" onclick="readMoreClick('${list.contentId}','${list.contentTypeId }');"
								style="color: #5ad67d; margin-top:20px;">Read More</button>
					<button type="button" class="btn btn-link" id="delete-bookmark" onclick="deleteBookmark('${userInfo.userId}','${list.contentId }');"
								style="color: #5ad67d; margin-top:20px;">삭제</button>
				</div>
			</div>
			</c:if>
			</c:forEach>
		</div>
	</main>
	</div>
</body>
</html>