package com.homework.java.servlet;


import com.homework.java.Model.Student;
import com.homework.java.Model.Submit;
import com.homework.java.jdbc.StudentJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HomeworkDeleteServlet")
public class HomeworkDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

//        String h_id = (String) req.getParameter("h_id");
//        StudentJdbc student_jdbc = new StudentJdbc();

        resp.sendRedirect("HomeworkListServlet");
    }
}
