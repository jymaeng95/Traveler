
$(document).ready(function(){
	var firebaseConfig = {
			apiKey: "AIzaSyDTO8C1ikYD7hwshXvokduQC1EE_xsNx6E",
			authDomain: "traveler-accompany-chat.firebaseapp.com",
			databaseURL: "https://traveler-accompany-chat.firebaseio.com",
			projectId: "traveler-accompany-chat",
			storageBucket: "traveler-accompany-chat.appspot.com",
			messagingSenderId: "799877741317",
			appId: "1:799877741317:web:eca19c7760f0576602556c",
			measurementId: "G-NHE6JKV1ST"
	};
	var sessionId = $("#userIdside").val();
	var userPic = $(".user-pic").find("img").attr("src");
	var accBno = $("#btnChat").val();
	
	console.log(sessionId);
	console.log(userPic);
	console.log(accBno);
	
//	Initialize Firebase
	firebase.initializeApp(firebaseConfig);
	firebase.analytics();
	
//	$("#btnChat").click(function(){
//		$(this).hide();
//	});	
	firebase.database().ref("messages/"+accBno).on("child_added",function(snapshot){
		if(snapshot.val().sender ==  sessionId) {
			var html = '<span class="chatter_msg_item chatter_msg_item_user">'
				+'<a href="#" class="chatter_avatar">'
				+'<img src="'+snapshot.val().userProfile+'" /></a>'
				+'<strong class="chatter_name">'+snapshot.val().sender+'</strong>'
				+snapshot.val().message+'</span>';
			$(".chatter_convo").append(html);
		}
		else  {
			var html = '<span class="chatter_msg_item chatter_msg_item_admin">'
				+'<a href="#" class="chatter_avatar">'
				+'<img src="'+snapshot.val().userProfile+'" /></a>' 
				+'<strong class="chatter_name">'+snapshot.val().sender+'</strong>'
				+snapshot.val().message+'</span>';
			$(".chatter_convo").append(html);
		}
	});
});
$(document).on("click","#btnChat",function(){
	var sessionId = $("#userIdside").val();
	var accBno = $("#btnChat").val();
	
	$(this).hide();
	$("#btnChat-out").show();
//	readMessage(accBno,sessionId);
	$(".chat").show();

});
$(document).on("keypress","#ta-message",function(event){
	var message = document.getElementById("ta-message").value;
	var sessionId = $("#userIdside").val();
	var userPic = $(".user-pic").find("img").attr("src");
	var accBno = $("#btnChat").val();
	if(event.keyCode == 13){
		$("#chat-form").submit(sendMessage(accBno, sessionId, userPic,message));
		$("#ta-message").val('');
		$('.chatter_convo').scrollTop($('.chatter_convo')[0].scrollHeight);
	}
});
$(document).on("click","#btnChat-out",function(){
//	$(".chatter_convo").empty();
	$("#btnChat").show();
	$(this).hide();
	$(".chat").hide();
});
//userId 받기 
// 채팅방 참가할때 붙여주기 
// 채팅방 나갈때 view에선 채팅 내용 날려주기 

function sendMessage(accBno, sessionId, userPic,message) {
	//get MEssage
	
	//save in databse
	firebase.database().ref("messages/"+accBno).push().set({
		"sender":sessionId,
		"message":message,
		"userProfile":userPic,
		"timestamp":new Date().getTime()
	});
	return false;
}

