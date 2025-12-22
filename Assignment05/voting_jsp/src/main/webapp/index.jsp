<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form method="post" action="login.jsp">
		Email: <input type="text" name="email"/> <br/><br/>
		Password: <input type="password" name="passwd"/> <br/><br/>
		<input type="submit" value="Sign In"/>
		<a href="newuser.jsp">Sign Up</a> 
	</form>
</body>
</html>
