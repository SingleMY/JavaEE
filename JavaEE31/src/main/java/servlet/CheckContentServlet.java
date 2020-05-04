package main.java.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckContentServlet")
public class CheckContentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String teacher_name = (String) req.getSession().getAttribute("teacher_name");
        String homework_title = (String) req.getParameter("homework_title");
        String student_name = (String) req.getParameter("student_name");
//        Teacher teacher = new Teacher(teacher_name);
//        TeacherJdbc teacher_jdbc = new TeacherJdbc();
//        String content = teacher_jdbc.CheckContent(homework_title, teacher, student_name);
//        req.setAttribute("content", content);

        req.getRequestDispatcher("homework_content.jsp").forward(req, resp);
    }
}
