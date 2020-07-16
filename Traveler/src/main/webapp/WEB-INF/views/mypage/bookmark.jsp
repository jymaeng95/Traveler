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
				<!--Read More 부분 contentId contentTypeID 받아서 spot쪽으로 이동하기 -->
				<div class="description">
					<h1><c:out value="${list.title }"></c:out></h1>
					<h2><c:out value="${list.addr }"></c:out></h2>
					<p><c:out value="${list.overview }"></c:out></p>
					<p class="read-more">
						<a href="#">Read More</a>
					</p>
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
					<p class="read-more">
						<a href="#">Read More</a>
					</p>
				</div>
			</div>
			</c:if>
			</c:forEach>
		</div>
	</main>
	</div>
</body>
</html>