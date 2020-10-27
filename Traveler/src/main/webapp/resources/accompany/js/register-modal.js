$(document).ready(function(){
   //$("#contact-submit").on("click",function(){
   //   window.parent.location.href('/accompany/index');
   //});
   $(".fa-search-plus").on("click", function() {
      var planNo;
      var title;
      var acc_bno;
      var hostId;

      $(this).parent().parent().each(function(){
         planNo = $(this).siblings("input[name=accPlanNo]").val();
         title = $(this).siblings("input[name=accTitle]").val();
         acc_bno = $(this).siblings("input[name=accBno]").val();
         hostId = $(this).siblings("input[name=accHostId]").val();
      });
      //폼 생성 후 전송 
      var form = document.createElement("form");
      form.setAttribute("name", "content_form");
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
      form.target="_parent";
      console.log(planNo +" "+ hostId +" "+ title +" "+ acc_bno);
      form.submit();
   });
   
});
function checkForm(){ 
    document.content_form.target="_parent"; 
    document.content_form.submit(); 
}