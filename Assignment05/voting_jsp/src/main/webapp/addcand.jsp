<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sunbeam.daos.CandidateDaoImpl, com.sunbeam.pojos.Candidate"%>
<%
    String name = request.getParameter("name");
    String party = request.getParameter("party");
    int votes = Integer.parseInt(request.getParameter("votes"));

    try (CandidateDaoImpl candDao = new CandidateDaoImpl()) {
        Candidate c = new Candidate(0, name, party, votes); 
        int count = candDao.save(c);
        if (count > 0) {
            response.sendRedirect("result.jsp");
        } else {
            out.println("<h3>Failed to add candidate!</h3>");
            out.println("<a href='newcand.jsp'>Try Again</a>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
        out.println("<a href='newcand.jsp'>Try Again</a>");
    }
%>