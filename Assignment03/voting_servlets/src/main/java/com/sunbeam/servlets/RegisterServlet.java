package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("register.html");   // show form only
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
        String fname = req.getParameter("first_name");
        String lname = req.getParameter("last_name");
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");
        String dobStr = req.getParameter("dob");

        Date dob = Date.valueOf(dobStr);

        try (UserDao userDao = new UserDaoImpl()) {

          User u = new User(
                0,               // id (auto-increment)
                fname,
                lname,
                email,
                passwd,
                dob,
                false,           // status
                "voter"          // role
            );

            u.setFirstName(fname);
            u.setLastName(lname);
            u.setEmail(email);
            u.setPasswd(passwd);
            
            System.out.println(u.getPasswd());
            
            u.setBirth(dob);

            // IMPORTANT: set default values
            u.setStatus(false);     // 0
            u.setRole("voter");

            int cnt = userDao.save(u);

            if(cnt > 0)
            {
            	out.println("<a href=index.html>login Now</a>");
            }
            //resp.sendRedirect("index.html");   // after successful register

        } catch (Exception e) {
            e.printStackTrace();
            //resp.sendRedirect("register.htm?error=1");
        }
        
       
    }
}
