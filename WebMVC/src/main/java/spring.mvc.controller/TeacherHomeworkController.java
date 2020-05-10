package spring.mvc.service;

import Bean.Homework;
import Bean.Student;
import Bean.Teacher;
import Bean.sHomework;
import jdbc.StudentJdbc;
import jdbc.TeacherJdbc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
@Scope("prototype")
public class TeacherHomeworkController {
    private static  ApplicationContext applicationContext;
    private static  TeacherJdbc teacher_jdbc;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        teacher_jdbc =  (TeacherJdbc) applicationContext.getBean("teacher_jdbc");

    }
    @RequestMapping("homeworklist_teacher")
    public String  Teacher_Index(HttpServletRequest request){

        String nextPage = "404";
        //处理逻辑
        String tno = (String) request.getSession().getAttribute("tno");

        List<sHomework> homeworkList = teacher_jdbc.QueryHomework(tno);
        List<sHomework> shomeworkList = new ArrayList<sHomework>();
        for(sHomework homework: homeworkList) {

           int  count= teacher_jdbc.QuerySubmit(homework.getH_id(),tno).size();

            homework.setCount(count);

            shomeworkList.add(homework);
        }
        System.out.println(homeworkList.toString());

        request.setAttribute("shomework_list", shomeworkList);

        nextPage = "teacher";

        return nextPage;
    }

    @RequestMapping("studentlist")
    public  String  Student_list(HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String tno = (String) request.getSession().getAttribute("tno");

        List<Student> studentList = teacher_jdbc.QueryStudent(tno);

        request.setAttribute("student_list",studentList);

        nextPage = "studentlist";

        return nextPage;

    }

    @RequestMapping("addstudent")
    public  String  AddStudent(
             @RequestParam(value ="sno") String sno,
             @RequestParam(value = "sname") String sname,
             @RequestParam(value = "password") String password,
             HttpServletRequest request){
        String nextPage=null;

        String tno = (String)request.getSession().getAttribute("tno");
        Student student = (Student) applicationContext.getBean("student");
        student.setSno(sno);
        student.setSname(sname);
        student.setPassword(password);

        teacher_jdbc.AddStudent(student,tno);
        nextPage = "redirect:/studentlist";
        return nextPage;

    }

    @RequestMapping("checkhomework")
    public  String  CheckHomework(HttpServletRequest request){
        String nextPage=null;
        return nextPage;

    }

    @RequestMapping("addhomework")
    public  String AddHomework(@RequestParam("h_id") String h_id,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("deadline") String deadline,
                                @RequestParam("create_time") String create_time
                                 ,HttpServletRequest request){
        String nextPage=null;

        String tno = (String) request.getSession().getAttribute("tno");

        TeacherJdbc teacher_jdbc = (TeacherJdbc) applicationContext.getBean("teacher_jdbc");
        Homework homework = (Homework) applicationContext.getBean("homework");
        homework.setH_id(h_id);
        homework.setTitle(title);
        homework.setContent(content);
        homework.setDeadline(deadline);
        homework.setCreate_time(create_time);
        homework.setTno(tno);
        teacher_jdbc.AddHomework(homework);
        return nextPage;
    }


    @RequestMapping("homeworkdelete")
    public  String HomeworkDelete(HttpServletRequest request){
        String nextPage=null;
        return nextPage;
    }
}
