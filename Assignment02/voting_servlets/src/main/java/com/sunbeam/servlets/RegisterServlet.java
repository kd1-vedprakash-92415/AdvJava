package com.sunbeam.servlets;

import java.io.IOException;
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
        resp.sendRedirect("register.htm");   // show form only
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fname = req.getParameter("first_name");
        String lname = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String dobStr = req.getParameter("dob");

        Date dob = Date.valueOf(dobStr);

        try (UserDao userDao = new UserDaoImpl()) {

          User u = new User(
                0,               // id (auto-increment)
                fname,
                lname,
                email,
                password,
                dob,
                false,           // status
                "voter"          // role
            );

            u.setFirstName(fname);
            u.setLastName(lname);
            u.setEmail(email);
            u.setPasswd(password);
            u.setBirth(dob);

            // IMPORTANT: set default values
            u.setStatus(false);     // 0
            u.setRole("voter");

            userDao.save(u);

            resp.sendRedirect("index.html");   // after successful register

        } catch (Exception e) {
            e.printStackTrace();
            //resp.sendRedirect("register.htm?error=1");
        }
        
       
    }
}
