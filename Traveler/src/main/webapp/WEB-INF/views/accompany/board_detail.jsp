<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../includes/sidebar_setting.jsp" %>
<style>
/* Base Styles */

body{
   margin: 0;
   padding: 0;
   font-size: 16px;
   line-height: 1.4em;
   color: #222;
   background-color: rgba(247, 247, 247, 0.8);
}

img{
   max-width: 100%;
   height: auto;
   border-radius: 3px;
}

a{
   text-decoration: none;
   color: inherit;
}

/* Grid */

.section {
   clear: both;
   padding: 0;
   margin: 0;
}

.col {
   display: block;
   float:left;
   margin: 1% 0 1% 1.6%;
}
.col:first-child { margin-left: 0; }

.span_3_of_3 {
   width: 100%;
}
.span_2_of_3 {
   width: 66.1%;
}
.span_1_of_3 {
   width: 32.2%;
}

/* Header & Navigation */

header{
   max-width: 100%;
   background: #222; /*FallBack*/
   background: rgba(0,0,0,.6);
   padding: 20px;
}

 .logo{
    text-align: center;
    color: #fff; 
    font-size: 3em; 
    font-weight: 400; 
    font-family: 'Poiret One', cursive; 
}



/* Blog Posts */

.container{
   max-width: 1020px;
   margin: 0 auto;
   padding: 20px;
}

.blog-post{
   background: #fff;
   margin: 0 auto 2em auto;
   padding: 35px 30px;
   box-shadow: 5px 5px 15px -1px rgba(0,0,0,0.3);
}

.side-post{
   background: #fff;
   margin: 0 auto 1.5em auto;
   width: 15em;
   padding: 20px;
}

.blog-content{
   font-size: 1em;
   border-left: 5px solid #8EE5EE;
   padding-left: 15px;
   width:550px;
   overflow:hidden;
   word-wrap:break-word;
}

.side-content{
   font-size: .9em;
   margin-top: 8px;
   margin-bottom: 0;
}

.blog-title{
   font-size: 2.5em;
   margin-top: 5px;
}

.date{
   font-size: 1em;
   font-weight: 400;
   color: #9C9C9C;
   margin-top: -10px;
   text-decoration: underline;
   text-decoration-color: currentColor;
}

.post-link{
   color: #009ACD;
   font-style: italic;
   transition: all 0.4s;
}

.post-link:hover{
   color: #005c7b;
   text-decoration: underline;
   text-decoration-color: currentColor;
   cursor: pointer;
}

/* Button */

.button{
   display: block;
   padding: 15px 20px;
   margin-top: .8em;
   background: #009ACD;
   text-align: center;
   color: #fff;
   font-size: 1.4em;
   border-radius: 3px;
   transition: all .4s ease-in;
}

.button:hover{
   background: #005c7b;
}

.btnmenu {
   background: #FFFFFF;
   border:0;
   outline:0;
   margin:-50px 0px -20px;
}

</style>
</head>
<script type="text/javascript">
$(document).ready(function() {
   var strDate1 = $('.date').html();
   var date = new Date(strDate1);
   var totaldate = $('#totaldate').val();
   totaldate *= 1;
   $('.date').html(strDate1 + " 에서 " +
         date.getFullYear() + "-" + (date.getMonth()+1) + "-" + (date.getDate() + totaldate-1));
   $("#btnmodify").click(function(){
      var contentId = $('#contentId').html();
      var planNo = $('#planno').html();
      location.href = "/accompany/modify?contentId=" + contentId + "&planNo=" + planNo;
   });
   $("#btnlist").click(function(){
      var pageNum = $('#pagenum').val();
      location.href = "/accompany/board?boardtype=main";
   });
   $("#btnapply").click(function(){
      checkapply('${userInfo.userId}');
   });
   $("#sendbtn").click(function(){
      var id = $('#leader').html();
      sendpop(id);
   });
});
//쪽지보내기
function sendpop(rcver){   
   var url = "/mypage/popup_send?rcver="+rcver;
   var name = "popup";
   window.open(url,name,"width=500, height=500, resizable=yes");
}
function checkapply(sender){
   $.ajax({
      url : "/mypage/message/cntapply",
      type : "post",
       dataType : "text",
       data : {
          "sender" : sender,
          "bno" : $('#contentId').html()
       },
       success : function(data) {
          if(data == "") apply(sender);
          else alert(data);
       },
       error : function(error) {
          alert("error.");
       }
   });
}
//신청하기
function apply(sender) {
   if(sender == $('#leader').html()){
      alert("자신의 글입니다.");
      }
   else{
      if($('#cnt').html() >= $('#numperson').html()){
         alert("신청 인원을 넘었습니다.");}
      else{
         $.ajax({
            url : "/mypage/message/send",
            type : "post",
             dataType : "text",
             data : {
                "sender" : sender,
                "userId" : $('#leader').html(),
                "mcontent" : '(' + $('#title').html() + ') 여행에 동행을 신청합니다.',
                "isacc" : '1',
                "bno" : $('#contentId').html()
             },
             success : function(data) {
                if(data == "right") alert("신청했습니다");
                else alert(data);
             },
             error : function(error) {
                alert("error.");
             }
         });
      }
   }
}

//글 삭제
function deleteacc() {
   $.ajax({
      url : "/accompany/delete",
      type : "post",
       dataType : "json",
       data : {
          "contentId" : $('#contentId').html()
       },
       success : function(data) {
          alert("삭제했습니다.");
          location.href = "/accompany/board?boardtype=main";
       },
       error : function(error) {
          alert("error.");
       }
   });
}


</script>
<body>
<%@include file="../includes/sidebar.jsp"%>
<main>
<header>
   <h1 class="logo">${detail.title} ✍️</h1>
</header>
   
   <div class="container">
        <div class="section">
             <div class="col span_2_of_3">
         <div class="blog-post">
        <h1 class="blog-title" id="title">${planner.planTitle }</h1>
        <h2 class="date">여행 날짜 : ${detail.planDate }</h2><br/>
        <p class="blog-content">${detail.content }</p>
        <a href="#" class="button" id="btnapply">모집 신청하기</a>
                  </div>
                  <div class="blog-post" style="height:400px;">
        
        <h5>여행지</h5><br/>
        <div style="overflow:auto;height:250px;">
        
        <c:forEach var="i" begin="1" end="${detail.planTotalDate }">
        <div><p>Day${i }</p><c:forEach items="${userplan}" var="list" varStatus="status">
        <c:if test="${list.planDay eq i}">
        <p class="blog-content"><img src="${list.img_src}" alt="img" width="100" height="50">  ${list.title }</p>
        <input type="hidden" id="totaldate" value="${list.planTotalDate }"/>
        </c:if></c:forEach></div>
        
        </c:forEach></div>
      </div>
             </div>
            
             <aside class="col span_1_of_3">
                  <div class="side-post">
                       <p class="side-content">      
                       <c:if test="${userInfo.userId eq writer }">
                        <button onclick="deleteacc();" class="btnmenu">
                        <i class="fas fa-trash"></i><p>삭제</p></button>
                        <button id="btnmodify" class="btnmenu">
                        <i class="fas fa-pencil-alt"></i><p>수정</p></button></c:if>
                        <button id="btnlist" class="btnmenu"><i class="fas fa-list"></i><p>목록</p></button></p>
                  </div>
                  <div class="side-post">
                       <img src="http://placehold.it/800x400&text=img" alt="img">      
            <p class="side-content">리더와 쪽지로 대화해보세요.</p><p id="leader">${planner.userId }</p>
            <a href="#" class="post-link" id="sendbtn">쪽지 보내기</a>
                  </div>
            
                  <div class="side-post">
                        <p>여행 동반자들  (${cnt} / ${detail.numperson})</p><hr/>
                        <div style="overflow:auto;height:100px;">
                       <c:forEach items="${idlist }" var="list" varStatus="status">
                          <a href="#" onclick="sendpop('${list}')">${list }</a>
                       </c:forEach>
                        </div>
                  </div>
              </aside>
        </div>
   </div>
   <p id="contentId" style="display:none;">${contentId }</p>
   <p id="planno" style="display:none;">${planNo }</p>
   <p id="cnt" style="display:none;">${cnt }</p>
   <p id="numperson" style="display:none;">${detail.numperson}</p>
</main>
<script src="/resources/index/js/holder.js"></script>
</body>
</html>