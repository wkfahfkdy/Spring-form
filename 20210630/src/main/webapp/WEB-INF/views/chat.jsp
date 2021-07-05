<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	#msgArea {
		width:500px;
		height:400px;
		border: 1px solid black;
		overflow-x:hidden;
	}
</style>
</head>
<body>
	<div align="center">
		<div>
			<label><b>채팅방</b></label>
		</div>
		<div>
			<div id="msgArea" style="overflow:auto;">
			
			</div>
			<div>
				<div>
					<input type="text" id="msg" onkeypress="if(event.keyCode==13){buttonSend()}">
					<div>
						<button type="button" onclick="buttonSend()">전송</button>
					</div>
					<br>
					<button type="button" onclick="location.href='home'">Home</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	// 전송 버튼 이벤트
	function buttonSend() {
		sendMessage();
		$("#msg").val('');
	}
	
	var sock = new SockJS("http://192.168.0.100/formPrj/chat/");
	sock.onmessage = onMessage;
	sock.onopen = onOpen;
	sock.onclose = onClose;
	
	function sendMessage() {
		sock.send($("#msg").val());
	}
	
	// 서버에서 메시지 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		var sessionId = null; // 데이터 보낸 사람
		var message = null;
		
		var arr = data.split(":");
		
		for(var i = 0; i < arr.length; i++){
			console.log('arr[' + i + ']: ' + arr[i]);
		}
		
		var cur_session = '${name}'; // 현재 세션 로그인 한 사람
		console.log("cur_session : " + cur_session);
		
		sessionId = arr[0];
		message = arr[1];
		
/* 		var str = "<div>";
		str += "<div>";
		str += "<b>" + sessionId + " : " + message + "</b>";
		str += "</div></div>";
		
		$("#msgArea").append(str); */
		
 		// 로그인 한 클라이언트와 타 클라이언트 분류
		if(sessionId == cur_session){
			var str = "<div>";
			str += "<div>";
			str += "<b>" + sessionId + " : " + message + "</b>";
			str += "</div></div>";
			
			$("#msgArea").append(str);
			
			var divdiv = document.getElementById("msgArea");
			divdiv.scrollTop = divdiv.scrollHeight;
		} else {
			var str = "<div>";
			str += "<div>";
			str += "<b>" + sessionId + " : " + message + "</b>";
			str += "</div></div>";
			
			$("#msgArea").append(str);
			
			var divdiv = document.getElementById("msgArea");
			divdiv.scrollTop = divdiv.scrollHeight;
		} 
	}
	
	// 채팅창 나갔을 때
	function onClose(evt){
		
		// var user = '${pr.username}';
		var user = '${name}';
		var str = user + " 님이 퇴장";
		
		$("#msgArea").append(str);
		
	}
	
	// 채팅창 들어왔을 때
	function onOpen(evt) {
		
		// var user = '${pr.username}';
		var user = '${name}';
		var str = user + " 님이 입장";
		
		$("#msgArea").append(str);
	}
	
</script>
</html>