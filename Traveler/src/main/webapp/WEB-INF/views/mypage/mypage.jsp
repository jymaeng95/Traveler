<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>

<!-- Custom CSS for the '3 Col Portfolio' Template -->
<link href="/resources/mypage/css/mypage.css" rel="stylesheet">
<script src="/resources/mypage/js/mypage.js"></script>
<title>Insert title here</title>
<style>
#sidebar * {
	font-size: 1.32rem;
}

#myModal * {
	font-size: 1.32rem;
}

#show-sidebar {
	position: fixed;
	left: 0;
	top: 10px;
	border-radius: 0 4px 4px 0px;
	width: 35px;
	transition-delay: 0.3s;
	font-size: 1.5rem;
}
</style>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container">
			<header>

				<div class="container">

					<div class="profile">

						<div class="profile-image">

							<img id="user-img" src="${userInfo.user_img }" alt=""
								style="width: 50%;">

						</div>

						<div class="profile-user-settings">
							<input type="hidden" id="session-id" value=${userInfo.userId }>
							<h1 class="profile-user-name" id="user-id">
								<c:out value="${userInfo.nickname }"></c:out>
							</h1>

							<button class="btn-mypage profile-edit-btn" id="btn-modify">Edit
								Profile</button>
							<button class="btn-mypage profile-edit-btn" id="btn-photo">Change
								Photo</button>

							<button class="btn-mypage profile-settings-btn" id="delete-user"
								data-toggle="modal" aria-label="profile settings">
								<i class="fas fa-cog" aria-hidden="true"></i>
							</button>
						</div>
						<form action="/upload/user/img" method="post" id="upload-form"
							enctype="multipart/form-data">
							<input type="file" style="visibility: hidden;" name="userImg"
								id='file-upload' />
						</form>
						<div class="profile-stats">

							<!--북마크 개수 / 여행 갯수 / 등 수치 정보 넣으면 좋을듯  -->
							<ul>
								<li><span class="profile-stat-count">164</span> posts</li>
								<li><span class="profile-stat-count">188</span> followers</li>
								<li><span class="profile-stat-count">206</span> following</li>
							</ul>

						</div>

						<div class="profile-bio">

							<p>
								<span class="profile-real-name">Maeng Joon Young</span> 제주도 북마크 많이 모으기!📷✈️🏕️
							</p>
						</div>

					</div>
					<!-- End of profile section -->
				</div>
				<!-- End of container -->
			</header>

			<main>
				<div class="container">
					<div class="gallery">
						<c:forEach items="${b_list }" var="list">
							<div class="gallery-item" tabindex="0">
								<img id="bookmark_img" src="${list.img_src }"
									class="gallery-image" alt="">
								<div class="gallery-item-info"
									onclick="imgClick('${list.contentId}','${list.contentTypeId }');">
									<ul>
										<!--   <li class="gallery-item-likes"><span
											class="visually-hidden">Likes:</span><i class="fas fa-heart"
											aria-hidden="true"></i> 56</li> -->
										<!-- <li class="gallery-item-comments"><span
											class="visually-hidden">Comments:</span><i
											class="fas fa-comment" aria-hidden="true"></i> 2</li> -->
										<li class="gallery-item-likes" id="bmk-title"><span
											class="visually-hidden"> </span> <c:out
												value="${list.title }"></c:out></li>
									</ul>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="gallery">
						<button type="button" class="btn-mypage profile-edit-btn"
							id="more-bookmark" style="width: 90%; margin: auto;">
							More Bookmark</button>
					</div>
					<!-- End of gallery -->

				</div>
				<!-- End of container -->
			</main>
		</div>

	</main>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" id="myModal" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="myModalLabel">
						회원 탈퇴
						</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
				</div>
				<div class="modal-body">
					<input type="hidden" name="is_kakao" id="is_kakao"
						value="${userInfo.is_kakao }">
					<c:if test="${userInfo.is_kakao != 'Y'}">
						<input type="password" id="upw" name="userPw" style="width: 100%"
							placeholder="패스워드 입력">
					</c:if>
					<c:if test="${userInfo.is_kakao == 'Y'}">
						<p>카카오로 회원 탈퇴를 진행합니다.</p>
						<p>탈퇴하시겠습니까?</p>
					</c:if>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" id="confirm">예</button>
					<button class="btn btn-primary" type="button" data-dismiss="modal">아니요</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
