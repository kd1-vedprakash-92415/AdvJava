<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<h3>User Registration</h3>
	<jsp:useBean id="rb" class="com.sunbeam.beans.RegistrationBean"/>
	<jsp:setProperty name="rb" property="*" />
	<jsp:setProperty name="rb" property="status" value="false"/>
	<jsp:setProperty name="rb" property="role" value="voter"/>
	${rb.registerUser()}
	<c:choose>
		<c:when test="${rb.regStatus}">
			<p>User Registration is successful.</p>
			<a href="index.jsp">Login Here</a>
		</c:when>
		<c:otherwise>
			<p>User Registration is failed.</p>
			<a href="newuser.jsp">Register Again</a> | 
			<a href="index.jsp">Go To Login</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
