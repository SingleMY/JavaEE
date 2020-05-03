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

@WebServlet("/SubmitHomeworkServlet")
public class SubmitHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String h_id = (String) req.getParameter("h_id");
        String sno = (String) req.getSession().getAttribute("sno");
        String commit_time = (String) req.getParameter("commit_time");
        String commit_content = req.getParameter("commit_content");
        String finished = "是";
        StudentJdbc student_jdbc = new StudentJdbc();
        Submit sh = new Submit(h_id,sno,finished,commit_time,commit_content);
        student_jdbc.InsertSubmit(sh);
        resp.sendRedirect("StudentHomeworkListServlet");
    }
}
