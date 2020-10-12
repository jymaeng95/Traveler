/**
 * 
 */
$(document).ready(function(){
   var map = initTmap();
   var ex_marker = new Array();
   var ex_polyline = new Array();
   var allInfo = new Array();
   var schedule = new Array();
   var start = 9;
   var end = 10;
   var userId = $("#uid").val();
   var planTitle = $("#p_title").text();
   var planNo = $("#planno").val();
   $('#plan-info').find('ul').find('input').each(function(){
      allInfo.push(JSON.parse($(this).val()));
   });
   var firstDate = allInfo[0].planDate.split('-');
   for(var i=0;i<allInfo.length;i++){
      console.log(allInfo[i]);
      var data = {};
      var date;
      data.text = allInfo[i].title;
      data.is_insertAfter = allInfo[i].is_insertAfter;
//      data.planDay = allInfo[i].planDay;
//      data.planTotalDate = allInfo[i].planTotalDate;
//      console.log("check: " + allInfo[i].planDay + " " + allInfo[i].planTotalDate);
      if(allInfo[i].startDate =="" || allInfo[i].endDate==""){
         date = allInfo[i].planDate.split('-');
         if(firstDate[0] != date[0] || firstDate[1] != date[1] || firstDate[2] != date[2]){
            firstDate = date;
            start = 9;
            end = 10;
         }
         data.startDate = new Date(date[0], (date[1]-1), date[2], start, 00);
         data.endDate = new Date(date[0], (date[1]-1), date[2], end, 00);
         console.log('start:'+start);
         console.log('end:'+end);
         start += 2; end+=2;
         schedule.push(data);
      }else {
         date = allInfo[i].startDate.split('-');
         data.startDate = new Date(date[0], (date[1]-1), date[2], date[3], date[4]);
         date = allInfo[i].endDate.split('-');
         data.endDate = new Date(date[0], (date[1]-1), date[2], date[3], date[4]);
         data.description = allInfo[i].descript;
         schedule.push(data);
      }
   }
   console.log(schedule);
   $(".dx-tab").on("click",function(){
//      alert("1234");
      var type = $(this).find("span.dx-tab-text").text();
      console.log(type);
      if(type == "agenda"){
         $(".dx-scheduler-navigator").css("visibility : visible;");
      }else {
         $(".dx-scheduler-navigator").css("visibility : hidden;");
      }
   })

   $("#btn-plan").on("click",function(){
      $("#btn-plan").attr("disabled",true);
      $("#btn-planner").attr("disabled",false);
      $('#planner-info').hide();
      $('#plan-info').show();

   });

   $("#btn-planner").on("click" , function(){
      $("#btn-planner").attr("disabled",true);
      $("#btn-plan").attr("disabled",false);
      $('#planner-info').show();
      $('#plan-info').hide();
   });

   $("input[name='plannerImg']").change(function(){
      var form = new FormData(document.getElementById('upload-form'));
      $.ajax({
         url : "/upload/plan/img",
         type: "POST",
         enctype: 'multipart/form-data',
         processData: false,
         contentType: false,
         dataType : 'json',
         data: form,
         success : function(data){
            $("#img-planner").attr("src",data.planImg);
         },
         error : function(error) {
            alert('이미지 전송 실패');
         }
      });
   });

   $("#modify-planner").on("click",function(){
      $.ajax({
         url : "/plan/update/planner",
         type : "post",
         dataType : 'text',
         data: {planNo : $("#planNo").val(), planTitle : $("#p_title").text(), info : $("#input-info").val()},
         success : function(data) {
//            alert(data)
            if(data === "success")   alert("성공적으로 업데이트 되었습니다.")
            else alert("업데이트에 실패하였습니다.")
         },
         error : function(error) {
            alert("잠시후 이용해주세요");
         }

      });
   });

//   $("#plan-info").find('ul').each(function(){
//      $(this).sortable();
//      $(this).disableSelection();
//   });

   $('#plan-info').find('button').click(function(){
      if(ex_marker != "") {
         for(var marker of ex_marker)
            marker.setMap(null);
      }
      var jsonData = new Array();
      var viaData = new Array();
      $(this).siblings('ul').find('input[name=planData]').each(function(){
         jsonData.push(JSON.parse($(this).val()));
      });

     for(var i=0;i<jsonData.length;i++){
        ex_marker.push(addMarkers(map,jsonData[i]));
     }
   });

   var calendarDate = allInfo[0].planDate.split('-');
   var instance;

   var date = allInfo[0].planDate.split('-');
   var day = date[2] - (allInfo[0].planDay - 1);
   if($("#modify-state").val() == 'Y') {
      instance = $("#scheduler").dxScheduler({
         dataSource: schedule,
         views: [{name: "agenda", type: "agenda"}],
            currentView: "agenda", 
            currentDate: new Date(calendarDate[0], (calendarDate[1]-1), calendarDate[2]),
            startDayHour: 9,
            showAllDayPanel : false,
            height: 600,
            onAppointmentDeleting: function (e) {  
               e.cancel = new $.Deferred();  
               e.cancel.resolve(true);
               if(e.appointmentData.is_insertAfter == 'N') {
//                  alert("adsf")
                  e.cancel.resolve(true);
               } else {
                  e.cancel.resolve(false);
               }
            },
            editing: {
               allowDeleting:false
            },
            onAppointmentFormOpening: function(e) {
               e.popup.option('showTitle', true);
               e.popup.option('title', e.appointmentData.text ? 
                     e.appointmentData.text : 
               'Create a new appointment');
               const form = e.form;
               let formItems = form.option("items");
               if(e.appointmentData.is_insertAfter == 'N') {
                  formItems[0].items[0].visible = false;
               } else {
                  formItems[0].items[0].visible = true;
               }
               form.option("items", formItems); 
            },
//            onAppointmentFormOpening: function(e) {
//            if(e.targetedAppointmentData.is_insertAfter == 'Y') {
//            e.component.option("editing.allowDeleting", true);
//            }
//            },
            onAppointmentAdded: function(e){  
               console.log(e.appointmentData);  
            } 
      }).dxScheduler("instance");
   } else {
      console.log(calendarDate[0] + " " + (calendarDate[1]-1) + " " + calendarDate[2]);
      instance = $("#scheduler").dxScheduler({
         dataSource: schedule,
         views: [{name: "agenda", type: "agenda"}],
            currentView: "agenda", 
            currentDate: new Date(date[0], date[1]-1, day),
            startDayHour: 9,
            showAllDayPanel : false,
            height: 600,
            onAppointmentDeleting: function (e) {  
               e.cancel = new $.Deferred();  
               e.cancel.resolve(true);
               if(e.appointmentData.is_insertAfter == 'N') {
//                  alert("adsf")
                  e.cancel.resolve(true);
               } else {
                  e.cancel.resolve(false);
               }
            },
            editing: {
               allowDeleting:true
            },
            onAppointmentFormOpening: function(e) {
               e.popup.option('showTitle', true);
               e.popup.option('title', e.appointmentData.text ? 
                     e.appointmentData.text : 
               'Create a new appointment');
               const form = e.form;
               let formItems = form.option("items");
               if(e.appointmentData.is_insertAfter == 'N') {
                  formItems[0].items[0].visible = false;
               } else {
                  formItems[0].items[0].visible = true;
               }
               form.option("items", formItems); 
            },
//            onAppointmentFormOpening: function(e) {
//            if(e.targetedAppointmentData.is_insertAfter == 'Y') {
//            e.component.option("editing.allowDeleting", true);
//            }
//            },
            onAppointmentAdded: function(e){  
               console.log(e.appointmentData);  
            } 
      }).dxScheduler("instance");
   }

});

function initTmap(){
   var map = new Tmapv2.Map("map_div",  
         {
      center: new Tmapv2.LatLng(33.38260485180545, 126.57321166992229), // 지도 초기 좌표
      width: "100%", 
      height: "400px",
      zoom: 10
         });

   return map;
} 

function addMarkers(map,planInfo){
	if(planInfo.addr == null) {
		planInfo.addr="주소 없음";
	}

	//Marker 객체 생성.
	var marker = new Tmapv2.Marker({
		position : new Tmapv2.LatLng(planInfo.mapX,planInfo.mapY), //Marker의 중심좌표 설정.
		map : map, //Marker가 표시될 Map 설정.
		title : planInfo.title //Marker 타이틀.
	});
	return marker;
}


function calcPlanDay(allInfoStartDay, instanceStartDay, allInfoPlanDay){
	var stDate = new Date(parseInt(allInfoStartDay.substr(0,4)), parseInt(allInfoStartDay.substr(5,2))-1, parseInt(allInfoStartDay.substr(8,2)));
    var endDate = new Date(parseInt(instanceStartDay.substr(0,4)), parseInt(instanceStartDay.substr(5,2))-1, parseInt(instanceStartDay.substr(8,2)));
    var flag = stDate <= endDate;
    var result = 0;
    if(flag) {
    	var btMs = endDate.getTime() - stDate.getTime();
    	btDay = btMs / (1000*60*60*24); // 두 날짜 차이 계산
    	result = btDay + parseInt(allInfoPlanDay);
    } else {
    	var btMs = stDate.getTime() - endDate.getTime();
    	btDay = btMs / (1000*60*60*24); // 두 날짜 차이 계산
    	result = parseInt(allInfoPlanDay) - btDay;
    }
	return result;
}