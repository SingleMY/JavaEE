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

@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String tno = (String) req.getSession().getAttribute("tno");

        TeacherJdbc teacher_jdbc = new TeacherJdbc();
        List<Student> student_list = teacher_jdbc.QueryStudent(tno);
        req.setAttribute("student_list", student_list);

        req.getRequestDispatcher("StudentList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

