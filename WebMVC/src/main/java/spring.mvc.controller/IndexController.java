package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.dao.StudentDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("prototype")
@RequestMapping("/")
public class IndexController {
//    //单例模式
//    private  static   ApplicationContext applicationContext;
//    static {
//        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//    }
   @Autowired
   StudentDao studentDao;


    @RequestMapping(value = "index")
    public String Login(@RequestParam("login_no") String login_no,
                        @RequestParam("login_password") String login_password,
                        @RequestParam("login_identity") String login_identity,
                        HttpServletRequest request, HttpServletResponse response) {


        String page = "404";
        if (login_identity.equals("学生")) {


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
                page = "index";
            }

        } else {
            TeacherJdbc teacher_jdbc = (TeacherJdbc) applicationContext.getBean("teacher_jdbc");
            if (teacher_jdbc.Teacher_login(login_no, login_password)) {
//                List<sHomework> shomework_list = teacher_jdbc.QueryHomework(login_no);
//                request.setAttribute("shomework_list", shomework_list);
                request.getSession().setAttribute("tno", login_no);
                page = "redirect:/homeworklist_teacher";
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
            Student student = (Student) applicationContext.getBean("student");
            student.setSno(register_no);
            student.setSname(register_name);
            student.setPassword(register_password);

            StudentJdbc student_jdbc = (StudentJdbc) applicationContext.getBean("student_jdbc");
            student_jdbc.InsertStudent(student);
            page = "index";
        }else if (register_identity.equals("教师")){
            Teacher teacher = (Teacher) applicationContext.getBean("teacher");
            teacher.setTno(register_no);
            teacher.setTname(register_name);
            teacher.setPassword(register_password);
            TeacherJdbc teacher_jdbc = (TeacherJdbc)applicationContext.getBean("teacher_jdbc");
            teacher_jdbc.AddTeacher(teacher);
            page = "index";
        }

        return page;
    }
}