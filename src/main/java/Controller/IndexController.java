package Controller;
import jdbc.StudentJdbc;
import jdbc.TeacherJdbc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.Model.Student;
import  main.java.Model.Teacher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import main.java.Model.Homework;
@Controller
public class IndexController {
    @RequestMapping(value = "index")
    public String Login(@RequestParam("login_no") String login_no,
                        @RequestParam("login_password") String login_password,
                        @RequestParam("login_identity") String login_identity,
                        HttpServletRequest request, HttpServletResponse response) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        String page = "404";
        if (login_identity.equals("学生")) {
            StudentJdbc student_jdbc = new StudentJdbc();
            if (student_jdbc.Student_login(login_no, login_password)) {
                //登录成功
//                List<Homework> homework_list = student_jdbc.QueryHomework(login_no);
//                request.setAttribute("code", "200");
//                request.setAttribute("status", "登陆成功");
//                request.setAttribute("homework_list", homework_list);
                request.getSession().setAttribute("sno", login_no);
                page = "redirect:/homeworklist_student";
            } else {
                request.setAttribute("code", "400");
                request.setAttribute("status", "登陆失败");
                request.setAttribute("homework_list", null);
                page = "student";
            }

        } else {
            TeacherJdbc teacher_jdbc = new TeacherJdbc();
            if (teacher_jdbc.Teacher_login(login_no, login_password)) {
                List<Homework> homework_list = teacher_jdbc.QueryHomework(login_no);
                request.setAttribute("homework_list", homework_list);
                request.getSession().setAttribute("tno", login_no);
                page = "teacher";
            }
        }
        return page;
    }


    @RequestMapping(value = "register")
    public String Register(@RequestParam("register_no") String register_no,
                        @RequestParam("register_name") String register_name,
                        @RequestParam("register_password") String register_password,
                        @RequestParam("register_identity") String register_identity,
                        HttpServletRequest request) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        String page = "404";
        if(register_identity.equals("学生")) {
            Student student = new Student(register_no,register_name,register_password);
            StudentJdbc student_jdbc = new StudentJdbc();
            student_jdbc.InsertStudent(student);
            page = "index";
        }else if (register_identity.equals("教师")){
            Teacher teacher = new Teacher(register_no,register_name,register_password);
            TeacherJdbc teacher_jdbc = new TeacherJdbc();
            teacher_jdbc.AddTeacher(teacher);
            page = "index";
        }

        return page;
    }
}