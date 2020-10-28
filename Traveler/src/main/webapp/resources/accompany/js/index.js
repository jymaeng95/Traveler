$(document).ready(function(){
   
   //$('[data-toggle="tooltip"]').tooltip();
   formatDate();
   
   $(".board-table tbody tr").click(function(){ 
      var tr = $(this);
       var td = tr.children();
      var hostId = td.eq(2).text();
      var planNo = td.eq(1).text();
      var acc_bno = td.eq(0).text();
      var title = td.eq(4).text();
      
      //폼 생성 후 전송 
      var form = document.createElement("form");
      form.setAttribute("method", "GET");  //Post 방식
      form.setAttribute("action", "/accompany/board_detail"); //요청 보낼 주소

      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", "planNo");
      hiddenField.setAttribute("value", planNo);
      form.appendChild(hiddenField);
      
        var titleField = document.createElement("input");
        titleField.setAttribute("type", "hidden");
        titleField.setAttribute("name", "title");
        titleField.setAttribute("value", title);
        form.appendChild(titleField);
        
        var acc_bnoField = document.createElement("input");
        acc_bnoField.setAttribute("type", "hidden");
        acc_bnoField.setAttribute("name", "acc_bno");
        acc_bnoField.setAttribute("value", acc_bno);
        form.appendChild(acc_bnoField);
        
        var hostIdField = document.createElement("input");
        hostIdField.setAttribute("type", "hidden");
        hostIdField.setAttribute("name", "hostId");
        hostIdField.setAttribute("value", hostId);
        form.appendChild(hostIdField);

      document.body.appendChild(form);
      console.log(planNo +" "+ hostId +" "+ title +" "+ acc_bno);
      form.submit();
   });
   
   $(".btnRead").on("click",function(){
           var acc_bno = $(this).val();
           var planNo = $(this).siblings('#planNo').val();
         var hostId = $(this).siblings('#hostId').val();
           var title = $(this).siblings('#title').val();
         //폼 생성 후 전송 
         var form = document.createElement("form");
         form.setAttribute("method", "GET");  //Post 방식
         form.setAttribute("action", "/accompany/board_detail"); //요청 보낼 주소

         var hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "planNo");
         hiddenField.setAttribute("value", planNo);
         form.appendChild(hiddenField);
         
           var titleField = document.createElement("input");
           titleField.setAttribute("type", "hidden");
           titleField.setAttribute("name", "title");
           titleField.setAttribute("value", title);
           form.appendChild(titleField);
           
           var acc_bnoField = document.createElement("input");
           acc_bnoField.setAttribute("type", "hidden");
           acc_bnoField.setAttribute("name", "acc_bno");
           acc_bnoField.setAttribute("value", acc_bno);
           form.appendChild(acc_bnoField);
           
           var hostIdField = document.createElement("input");
           hostIdField.setAttribute("type", "hidden");
           hostIdField.setAttribute("name", "hostId");
           hostIdField.setAttribute("value", hostId);
           form.appendChild(hostIdField);

         document.body.appendChild(form);
         console.log(planNo +" "+ hostId +" "+ title +" "+ acc_bno);
         form.submit();
 });

   //paging
   
   var actionForm = $("#actionForm");
   $(".paginate_button a").on("click",function(e){
      e.preventDefault();
      console.log('click');
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));
      actionForm.submit();
    });

    var searchForm = $("searchForm");
    $("searchForm button").on("click", function(e){
      if(!searchForm.find("option:selected").val()){
         alert("검색종류를 입력하세요");
         return false;

      }
      if(!searchForm.find("input[name='keyword']").val()){
         alert("키워드를 입력하세요");
         return false;
      }

      searchForm.find("input[name='pageNum']").val("1");
      e.preventDefault();
      searchForm.submit();
   });
});

$(document).on("click","#btn-register",function(){
   $("#modal").modal("show");
});
$(document).on("click","#confirm",function(){
   //폼 생성 후 전송 
   var planNo = $('#planTitle option:selected').val();   
   var form = document.createElement("form");
   form.setAttribute("method", "GET");  //Post 방식
   form.setAttribute("action", "/accompany/register"); //요청 보낼 주소

   var hiddenField = document.createElement("input");
   hiddenField.setAttribute("type", "hidden");
   hiddenField.setAttribute("name", "planNo");
   hiddenField.setAttribute("value", planNo);
   form.appendChild(hiddenField);

   document.body.appendChild(form);
   console.log(planNo);
   form.submit();
});

//날짜 변환 함수
function formatDate(){
   var curDate = $("[id^=date]");
   curDate.each(function(i) {
      var cdate = $("#date"+i).html();
      var today, resultDate;
      today = new Date();
      resultDate = new Date(cdate);
      timegap = (today - resultDate)/(60*60*1000);
      
      var curYear = resultDate.getFullYear();
      var curMonth = (resultDate.getMonth() + 1);
      var curDay = resultDate.getDate();
   
      if (timegap <= 24) {
         if (Math.floor(timegap) == 0) {
            resultDate = Math.floor(timegap * 24) + '분 전';
         }
         else {
            resultDate = Math.floor(timegap) + '시간 전';
         }
      }
      else {
         resultDate = curYear + '-' + curMonth + '-' + curDay;
      }
      $("#date"+i).html(resultDate);
      
   });
   
}