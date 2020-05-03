package servlet;

import jdbc.TeacherJdbc;
import main.java.Model.Homework;
import jdbc.StudentJdbc;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String login_no = (String) req.getParameter("login_no");
        String login_password = (String) req.getParameter("login_password");
        String login_identity = (String) req.getParameter("login_identity");

        System.out.println(login_no);
        if(login_identity.equals("学生")) {
            StudentJdbc student_jdbc = new StudentJdbc();
            if(student_jdbc.Student_login(login_no,login_password)){
                //登录成功
                List<Homework> homework_list = student_jdbc.QueryHomework(login_no);
                req.setAttribute("homework_list", homework_list);
                req.getSession().setAttribute("sno",login_no);
                req.getRequestDispatcher("student.jsp").forward(req, resp);
            }else{
                PrintWriter pw = resp.getWriter();
                pw.write("登录失败，用户名不存在或密码错误！");
                pw.flush();
            }

        }else {
            TeacherJdbc teacher_jdbc = new TeacherJdbc();
            if(teacher_jdbc.Teacher_login(login_no,login_password)){
                List<Homework> homework_list = teacher_jdbc.QueryHomework(login_no);
                req.setAttribute("homework_list", homework_list);
                req.getSession().setAttribute("tno",login_no);
                req.getRequestDispatcher("teacher.jsp").forward(req, resp);
            }
        }
    }
}
