<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>로그인</h1>
		</div>
		<c:if test="${not empty id }">
			<h1>${id }님, 이미 로그인ㅇ</h1>
			<button type="button" onclick="location.href='home'">Home</button>
		</c:if>
		<c:if test="${empty id }">
			<form:form modelAttribute="member" action="memberLogin" method="post">
				<label>이메일 : </label>
				<form:input path="email"/><br>
				<label>패스워드 : </label>
				<form:password path="password"/>
				<input type="submit" value="로그인"> 
			</form:form>
		</c:if>
	</div>
</body>
</html>