<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Form</title>
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
	</div>
</body>
</html>