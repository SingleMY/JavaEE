package com.homework.java.servlet;



import com.homework.java.Model.Student;
import com.homework.java.Model.Teacher;
import com.homework.java.jdbc.StudentJdbc;
import com.homework.java.jdbc.TeacherJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class
RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String register_no = (String) req.getParameter("register_no");
        String register_name = (String) req.getParameter("register_name");
        String register_password = (String) req.getParameter("register_password");
        String register_identity = (String) req.getParameter("register_identity");

        if(register_identity.equals("学生")) {
            Student student = new Student(register_no,register_name,register_password);
            StudentJdbc student_jdbc = new StudentJdbc();
            student_jdbc.InsertStudent(student);
        }else if (register_identity.equals("教师")){
            Teacher teacher = new Teacher(register_no,register_name,register_password);
            TeacherJdbc teacher_jdbc = new TeacherJdbc();
            teacher_jdbc.AddTeacher(teacher);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
