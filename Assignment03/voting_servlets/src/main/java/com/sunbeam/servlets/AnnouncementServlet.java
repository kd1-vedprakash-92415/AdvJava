package com.sunbeam.servlets;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/announce")
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get announcement from prev page
		String ann = request.getParameter("ann");
		// store announcement in servlet context -- attribute
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("annmsg", ann);
		// go to result servlet
		response.sendRedirect("result");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
