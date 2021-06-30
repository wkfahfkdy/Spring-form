<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
</style>
</head>
<body>
	<div align="center">
		<form:form action="memberRegister" modelAttribute="member" enctype="multipart/form-data" method="post">
			<label>email</label>
			<form:input path="email" /><br>
			
			<label>password</label>
			<form:password path="password"/><br>
			
			<label>passwordCheck</label>
			<input type="password" id="passwordCheck"><br>
			
			<label>이 름</label>
			<form:input path="name"/><br>
			
			<label>사 진</label>
			<input type="file" id="file" name="file"/><br>
			
			<label>회원구분</label>
			<form:select path="gubun" items="${loginType}"/>
			
			<input type="submit" value="가입">
		</form:form>
	</div>
</body>
</html>