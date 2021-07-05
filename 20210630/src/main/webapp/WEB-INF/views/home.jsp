<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
		function memberListCall(){
			
			$.ajax({
				url : 'api/members',
				type : 'get',
				dataType : 'json',
				success : function(res) {
					// 교수님 코드
					if(res.length > 0) {
						var title = $("<h1>회원목록<h1>");
						var tb = $("<table />");
						$.each(res, function(i, member){
							var row = $("<tr />").append(
								$("<td />").text(member.email),
								$("<td />").text(member.name),
								$("<td />").text(member.state)
							);
							tb.append(row);
						});
						$("#list").append(title);
						$("#list").append(tb);
						/* 
						// 내 코드
						console.log("res : " + res);
						
						var str = "";
						var result = res;
						$.each(result, function(i){
							str += result[i].email + ":";
							str += result[i].name + "<br>";
						});
						$('#list').append(str);
						*/
					}
				},
				error : function(err) {
					console.log("err : " + err)
				} 
					

			
			});
		}

</script>
</head>
<body>
	<div align="center">
		<div>
			<h1>Home</h1>
		<%-- 	one : ${encData } <br>
			two : ${encStr } --%>
			plainText : ${plainText }<br>
			cyperText : ${cyperText }<br>
			decryptionText : ${decryptionText }
		</div>
		<div>
			<h3><a href="memberInputForm">회원가입</a></h3>
		</div>
		<div>
			<h3><a href="step1">메시지 연습. step1</a></h3>
		</div>
		<div>
			<h3><a href="memberLoginForm">로그인</a></h3>
		</div>
		<div>
			<h3><a href="api/members">json data</a></h3>
		</div>
		<div>
			<h3><a href="chat">채팅방</a></h3>
		</div>
		<div>
			<h3><button type="button" onclick="memberListCall()">회원목록(JSON)</button></h3>
		</div>
		
		<div id ="list">
			
		</div>
	</div>
</body>
</html>