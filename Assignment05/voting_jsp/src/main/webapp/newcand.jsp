<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Candidate</title>
</head>
<body>
	<h3>Add New Candidate</h3>
	<form method="post" action="addcand.jsp">
		Name: <input type="text" name="name" required/> <br/><br/>
		Party: <input type="text" name="party" required/> <br/><br/>
		<input type="hidden" name="votes" value="0"/>
		<input type="submit" value="Add Candidate"/>
		<a href="result.jsp">Cancel</a>
	</form>
</body>
</html>