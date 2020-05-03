package com.homework.java.servlet;



import com.homework.java.Model.Student;
import com.homework.java.Model.Teacher;
import com.homework.java.jdbc.TeacherJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateHomeworkServlet")
public class UpdateHomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String tno = (String) req.getSession().getAttribute("tno");
//        Teacher teacher = new Teacher(teacher_name);
//        TeacherJdbc teacher_jdbc = new TeacherJdbc();
//        List<Student> student_list = teacher_jdbc.QueryStudent(teacher);
        //      req.setAttribute("student_list", student_list);

        resp.sendRedirect("HomeworkListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

