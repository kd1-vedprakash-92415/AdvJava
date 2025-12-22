<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sunbeam.daos.CandidateDaoImpl, com.sunbeam.pojos.Candidate"%>
<%
    int candId = Integer.parseInt(request.getParameter("candid"));
    Candidate c = null;
    try (CandidateDaoImpl candDao = new CandidateDaoImpl()) {
        c = candDao.findById(candId);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Candidate</title>
</head>
<body>
	<h3>View Candidate</h3>
	<% if (c != null) { %>
		<table border="1" cellpadding="5">
			<tr><th>ID</th><td><%= c.getId() %></td></tr>
			<tr><th>Name</th><td><%= c.getName() %></td></tr>
			<tr><th>Party</th><td><%= c.getParty() %></td></tr>
			<tr><th>Votes</th><td><%= c.getVotes() %></td></tr>
		</table>
	<% } else { %>
		<p>Candidate not found!</p>
	<% } %>
	<br/>
	<a href="result.jsp">Back to Results</a>
</body>
</html>