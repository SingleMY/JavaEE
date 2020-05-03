package com.homework.java.servlet;



import com.homework.java.Model.Homework;
import com.homework.java.Model.Teacher;
import com.homework.java.jdbc.TeacherJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddHomeworkServlet")
public class AddHomeworkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String tno = (String) req.getSession().getAttribute("tno");
        String h_id = (String) req.getParameter("h_id");
        String title = (String) req.getParameter("title");
        String content = (String) req.getParameter("content");
        String deadline = (String) req.getParameter("deadline");
        String create_time = (String) req.getParameter("create_time");
        TeacherJdbc teacher_jdbc = new TeacherJdbc();
        teacher_jdbc.AddHomework(new Homework(h_id,title,content,create_time,deadline,tno));
        resp.sendRedirect("HomeworkListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
