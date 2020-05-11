package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.dao.HomeworkDao;
import spring.mvc.dao.StudentDao;
import spring.mvc.dao.SubmitDao;
import spring.mvc.model.Homework;
import spring.mvc.model.Student;
import spring.mvc.model.Teacher;
import spring.mvc.model.sHomework;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
@Scope("prototype")
public class TeacherHomeworkController {
    @Autowired
    HomeworkDao homeworkDao;
    @Autowired
    SubmitDao submitDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    Student student;
    @Autowired
    sHomework shomework;
    @RequestMapping("homeworklist_teacher")
    public String  Teacher_Index(HttpServletRequest request){

        String nextPage = "404";
        //处理逻辑
        String tno = (String) request.getSession().getAttribute("tno");

        List<sHomework> homeworkList = homeworkDao.queryHomeworkByTno(tno);
        List<sHomework> shomeworkList = new ArrayList<sHomework>();
        for(sHomework homework: homeworkList) {

           int  count= submitDao.QuerySubmit(homework.getH_id(),tno).size();

            homework.setCount(count);

            shomeworkList.add(homework);
        }

        request.setAttribute("shomework_list", shomeworkList);

        nextPage = "teacher";

        return nextPage;
    }

    @RequestMapping("studentlist")
    public  String  Student_list(HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String tno = (String) request.getSession().getAttribute("tno");

        List<Student> studentList = studentDao.QueryStudent(tno);

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

        student.setSno(sno);
        student.setSname(sname);
        student.setPassword(password);

//        teacherDao.AddStudent(student,tno);
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


        shomework.setH_id(h_id);
        shomework.setTitle(title);
        shomework.setContent(content);
        shomework.setDeadline(deadline);
        shomework.setCreate_time(create_time);
        shomework.setTno(tno);
        homeworkDao.AddHomework(shomework);
        return nextPage;
    }


    @RequestMapping("homeworkdelete")
    public  String HomeworkDelete(HttpServletRequest request){
        String nextPage=null;
        return nextPage;
    }
}
