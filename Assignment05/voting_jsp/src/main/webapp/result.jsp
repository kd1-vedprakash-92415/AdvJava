<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
	<h3>Voting Result</h3>
	Hello, ${sessionScope.lb.user.firstName} ${lb.user.lastName}
	<hr/>
	<jsp:useBean id="clb" class="com.sunbeam.beans.CandidateListBean"/>
	${clb.fetchCandidates()}
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Party</th>
				<th>Votes</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${clb.candidateList}">
				<tr>
					<td>${c.id}</td>
					<td>${c.name}</td>
					<td>${c.party}</td>
					<td>${c.votes}</td>
					<td>
						<a href="editcand.jsp?candid=${c.id}">
							<img src="images/edit.png" alt="Edit" height="25px"/>
						</a>
						<a href="delcand.jsp?candid=${c.id}">
							<img src="images/delete.png" alt="Delete" height="25px"/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>