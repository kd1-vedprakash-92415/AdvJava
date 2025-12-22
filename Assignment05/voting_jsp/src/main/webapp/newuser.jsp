<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h3>User Registration</h3>
	<form method="post" action="register.jsp">
		First Name: <input type="text" name="fname"/> <br/><br/>
		Last Name: <input type="text" name="lname"/> <br/><br/>
		Email: <input type="text" name="email"/> <br/><br/>
		Password: <input type="password" name="passwd"/> <br/><br/>
		Birth Date: <input type="date" name="birth"/> <br/><br/>
		<input type="submit" value="Register"/> 
	</form>
</body>
</html>
