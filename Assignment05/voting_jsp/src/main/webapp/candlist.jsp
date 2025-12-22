<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Candidates</title>
</head>
<body>
	<h3>Candidate List</h3>
	
	<jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session"/>
	<jsp:useBean id="clb" class="com.sunbeam.beans.CandidateListBean"/>
	
	${clb.fetchCandidates()}
	
	<p><strong>Hello,</strong> <c:out value="${lb.user.firstName} ${lb.user.lastName}"/> 
	(<c:out value="${lb.user.email}"/>) 
	 <a href="logout.jsp" class="btn">Sign Out</a></p>
	<hr/>

	<c:choose>
		<c:when test="${lb.user.status == true}">
			<h4 class="voted">You have already voted. Thank you!</h4>
		</c:when>
		
		<c:otherwise>
			<h4 class="not-voted">Please select one candidate to vote:</h4>
			<form method="post" action="vote.jsp">
				<c:forEach var="c" items="${clb.candidateList}" varStatus="status">
					<label>
						<input type="radio" name="candidate" value="${c.id}" required/>
						<strong>${c.name}</strong>—${c.party}
						(${c.votes} vote<c:if test="${c.votes != 1}">s</c:if>)
					</label><br/><br/>
				</c:forEach>
				<input type="submit" value="Submit Vote" class="btn"/>
			</form>
		</c:otherwise>
	</c:choose>

	<br/><br/>
	<footer>
		<a href="index.jsp"></a>
	</footer>
</body>
</html>