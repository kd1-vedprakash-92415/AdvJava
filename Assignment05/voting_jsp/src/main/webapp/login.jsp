<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Auth</title>
</head>
<body>
	<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session"/>
	<jsp:setProperty name="lb" property="*"/>
	${lb.authenticate()}
	<c:choose>
		<c:when test="${empty lb.user}">
			<h3>Login Failed.</h3>
			<p>
				Invalid email or password.
			</p>
			<a href="index.jsp">Login Again</a>
		</c:when>
		<c:when test="${lb.user.role == 'admin'}">

			<c:redirect url="result.jsp"/>
		</c:when>
		<c:otherwise>
 			<c:redirect url="candlist.jsp"/>
		</c:otherwise>
	</c:choose>
</body>
</html>
