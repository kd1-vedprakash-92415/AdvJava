<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Login Page</h2>
	<jsp:useBean id="ur" class="com.sunbeam.beans.LoginBean"/>
	<jsp:setProperty name="ur" property="email" param="email"/>
	<jsp:setProperty name="ur" property="password" param="password"/>
	
	<%ur.authenticate(); %>
	<% if(ur.getUser()!= null){ %>
		Welcome <jsp:getProperty property="email" name="ur"/>
	<%}else{ %>
		Failed
		<%} %>
</body>
</html>