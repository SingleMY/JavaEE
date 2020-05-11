package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.dao.StudentDao;
import spring.mvc.dao.TeacherDao;
import spring.mvc.model.Student;
import spring.mvc.model.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("prototype")
public class IndexController {
//    //单例模式
//    private  static   ApplicationContext applicationContext;
//    static {
//        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//    }
   @Autowired
   StudentDao studentDao;
   @Autowired
   TeacherDao teacherDao;

   @Autowired
   Student student;
   @Autowired
   Teacher teacher;

    @RequestMapping(value = "index")
    public String Login(@RequestParam("login_no") String login_no,
                        @RequestParam("login_password") String login_password,
                        @RequestParam("login_identity") String login_identity,
                        HttpServletRequest request, HttpServletResponse response) {


        String page = "404";
        if (login_identity.equals("学生")) {


            if (studentDao.student_login(login_no, login_password)) {
                System.out.println("1");
                //登录成功
                request.getSession().setAttribute("sno", login_no);
                page = "redirect:/homeworklist_student";
            } else {

                request.setAttribute("code", "400");
                request.setAttribute("status", "登陆失败");
                request.setAttribute("homework_list", null);
                page = "index";
            }

        } else {
            if (TeacherDao.teacher_login(login_no, login_password)) {
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

        String page = "404";
        if(register_identity.equals("学生")) {

            student.setSno(register_no);
            student.setSname(register_name);
            student.setPassword(register_password);

            StudentDao.insertStudent(student);
            page = "index";
        }else if (register_identity.equals("教师")){

            teacher.setTno(register_no);
            teacher.setTname(register_name);
            teacher.setPassword(register_password);
            TeacherDao.AddTeacher(teacher);
            page = "index";
        }
        return page;
    }
}