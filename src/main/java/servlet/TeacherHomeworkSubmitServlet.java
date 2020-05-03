package main.java.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TeacherHomeworkSubmitServlet")
public class TeacherHomeworkSubmitServlet extends HttpServlet {
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
//        Teacher teacher = new Teacher(teacher_name);
//        TeacherJdbc teacher_jdbc = new TeacherJdbc();
//        List<Submit> submit_list = teacher_jdbc.QuerySubmit(homework_title, teacher);
//        req.setAttribute("submit_list", submit_list);

        req.getRequestDispatcher("homework_student.jsp").forward(req, resp);
    }
}
