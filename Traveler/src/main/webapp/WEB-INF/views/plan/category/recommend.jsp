<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

          <li class="header-menu">
            <span>관광지 추천</span>
          </li>
        <form method="GET" name="imgForm" id="imgForm">
         <c:forEach var="i" begin="0" end="4">
         <li>
            <hr>
            <div class="row spot_info">

                  <img class="img-responsive" id="photo${i}" onclick=""
                     style="cursor: pointer;" src="" alt="" width="150" height="100">

                  <h5 id="title${i}"></h5>
                  <h3 id="mapX${i }"></h3>
                  <h3 id="mapY${i }"></h3>

            </div>
            </li>
         </c:forEach>
      </form>