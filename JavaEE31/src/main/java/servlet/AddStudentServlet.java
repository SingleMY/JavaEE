package servlet;



import main.java.Model.Student;
import jdbc.TeacherJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String tno = (String) req.getSession().getAttribute("tno");
        String sno = (String) req.getParameter("sno");
        String sname = (String) req.getParameter("sname");
        String password = (String) req.getParameter("password");

        TeacherJdbc teacher_jdbc = new TeacherJdbc();
        teacher_jdbc.AddStudent(new Student(sno,sname,password),tno);

        resp.sendRedirect("StudentListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
