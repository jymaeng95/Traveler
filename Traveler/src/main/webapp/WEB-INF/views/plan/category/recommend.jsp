<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<li class="header-menu"><span>관광지 추천</span></li>
<form method="GET" name="imgForm" id="imgForm">
	<c:forEach var="i" begin="0" end="4">
		<li>
			<hr>
			<div class="row spot_info"">
				<div class="col-lg-5">
					<img class="img-responsive" id="r_photo${i}" onclick=""
						style="cursor: pointer;" src="" alt="" width="150" height="100">
				</div>
				<div class="col-lg-7">
					<h5 id="r_title${i}"></h5>
					<h6 id="r_addr${i }"></h6>
				</div>
			</div>
		</li>
	</c:forEach>
	<hr>
</form>
<div class="row text-center">
	<div class="col-lg-12">
		<ul class="pagination">
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

	<form id='actionForm' action="/plan/create" method='get'>
		<input type='hidden' name='pageNum' value='${pageMaker.pageNum }'>
	</form>
	<a></a>
</div>