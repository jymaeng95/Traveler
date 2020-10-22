$(document).ready(function(){
   //$("#contact-submit").on("click",function(){
   //   window.parent.location.href('/accompany/index');
   //});
});
function checkForm(){ 
    document.content_form.target="_parent"; 
    document.content_form.submit(); 
}