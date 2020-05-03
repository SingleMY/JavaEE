package servlet;



import main.java.Model.Homework;
import jdbc.StudentJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentHomeworkListServlet")
public class StudentHomeworkListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String sno = (String) req.getSession().getAttribute("sno");

        StudentJdbc student_jdbc = new StudentJdbc();
        List<Homework> homework_list = student_jdbc.QueryHomework(sno);
        req.setAttribute("homework_list", homework_list);
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
