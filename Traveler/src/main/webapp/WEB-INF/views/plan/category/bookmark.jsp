<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

          <li class="header-menu">
            <span>북마크</span>
          </li>
          <form method="GET" name="imgForm" id="imgForm">
        <c:forEach var="i" begin="0" end="4">
         <li>
            <hr>
            <div class="row spot_info">

                  <img class="img-responsive" id="b_photo${i}" onclick=""
                     style="cursor: pointer;" src="" alt="" width="150" height="100">

                  <h5 id="b_title${i}"></h5>
                  <h3 id="b_mapX${i }"></h3>
                  <h3 id="b_mapY${i }"></h3>

            </div>
            </li>
         </c:forEach>
         </form>