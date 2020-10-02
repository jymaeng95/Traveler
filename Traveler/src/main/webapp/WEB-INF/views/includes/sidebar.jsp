<script>
$(document).ready(function() {
	$.ajax({
		url : "/mypage/message/cntnoread",
		type : "post",
	 	dataType : "json",
	 	data : {
			"userId" : $('#userIdside').val()
	 		},
			success : function(data) {
				if(data != 0) $('#cnt').text(data);
			},
			error : function(error) {
				$('#cnt').text("?");
			}
	});
});
function prevent(){
	
	if($("#userIdside").val() == "") {
		alert("login First")
		location.href = "/login/index";
	}
	else location.href = "/accompany/index";
}
</script>
<div class="page-wrapper chiller-theme toggled">
	<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
		class="fas fa-bars"></i>
	</a>
	<nav id="sidebar" class="sidebar-wrapper">
		<div class="sidebar-content">
			<div class="sidebar-brand">
				<a href="/">Traveler</a>
				<div id="close-sidebar">
					<i class="fas fa-times"></i>
				</div>
			</div>
			<div class="sidebar-header">
				<div class="user-pic">
					<img class="img-responsive img-rounded" src="${userInfo.user_img }"
						alt="User picture">
				</div>
				<div class="user-info">

					<c:if test="${userInfo.userId == null }">
						<span class="user-name">After Login</span>
					</c:if>
					<c:if test="${userInfo.userId != null }">
						<span clss="user-name" id="user-name">${userInfo.nickname }</span>
						<span class="user-role">Administrator</span>
					</c:if>
				</div>
			</div>
			<!-- sidebar-header  -->

			<!-- sidebar-search  -->
			<div class="sidebar-menu">
				<ul>
					<c:if test="${userInfo.userId == null }">
						<li class="sidebar"><a href="/login/index"> <i
								class="fas fa-user"></i> <span>Login</span>
						</a></li>
					</c:if>
					<li class="header-menu"><span>General</span></li>
					<li class="sidebar"><a href="/plan/plan"> <i
							class="fas fa-plane"></i> <span>Plan</span>
					</a></li>
					<li class="sidebar"><a href="/spot/spot"> <i
							class="fas fa-map-marker-alt"></i> <span>Spot</span>
					</a></li>
					<!--                <li class="sidebar-dropdown"><a href="#"> <i -->
					<!--                      class="far fa-gem"></i> <span>Account</span> -->
					<!--                </a> -->
					<!--                   <div class="sidebar-submenu" style="display: none;"> -->
					<!--                      <ul> -->
					<!--                         <li><a href="#">General</a></li> -->
					<!--                         <li><a href="#">Panels</a></li> -->
					<!--                         <li><a href="#">Tables</a></li> -->
					<!--                         <li><a href="#">Icons</a></li> -->
					<!--                         <li><a href="#">Forms</a></li> -->
					<!--                      </ul> -->
					<!--                   </div></li> -->
					 <li class="sidebar"><a href="#" onclick="prevent();"> <i
	                    class="fas fa-handshake"></i> <span>Accompany</span> 
	              </a></li>
					<c:if test="${userInfo.userId != null }">
						<li class="sidebar"><a href="/budget/index"> <i
								class="fas fa-map-marker-alt"></i> <span>Budget</span>
						</a></li>
						<li class="sidebar-dropdown active"><a href="#"> <i
								class="fas fa-file-alt"></i> <span>Mypage</span>
						</a>
							<div class="sidebar-submenu" style="display: block;">
								<ul>
									<li><a href="/mypage/mypage">My Information</a></li>
									<li><a href="/mypage/bookmark">bookmark</a></li>
									<li><a href="/mypage/message">Message</a></li>
									<li><a href="#">Histogram</a></li>
									<li><a href="/etc/ToDoList">To-do List</a></li>
								</ul>
							</div></li>
					</c:if>

				</ul>
			</div>
			<!-- sidebar-menu  -->
		</div>
		<!-- sidebar-content  -->
		<div class="sidebar-footer">
			<c:if test="${userInfo.userId != null }">
				 <a href="/mypage/message"> <i class="fa fa-envelope"></i> <span
               class="badge badge-pill badge-success notification" id="cnt"></span>
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
		</div>
		<input type="hidden" id="userIdside" value="${userInfo.userId }"/>
	</nav>