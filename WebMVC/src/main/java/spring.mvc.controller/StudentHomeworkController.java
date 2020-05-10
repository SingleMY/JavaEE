package spring.mvc.service;

import Bean.Homework;
import Bean.Submit;
import Bean.sHomework;
import jdbc.StudentJdbc;
import jdbc.TeacherJdbc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentHomeworkController {
    @RequestMapping("homeworklist_student")
    public String  Student_Index(HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String sno = (String) request.getSession().getAttribute("sno");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentJdbc student_jdbc = (StudentJdbc) applicationContext.getBean("student_jdbc");

        List<sHomework> homeworkList = student_jdbc.QueryHomework(sno);
        List<sHomework> homework_list = new ArrayList<>();
        TeacherJdbc teacherJdbc = new TeacherJdbc();
        for(sHomework homework: homeworkList) {
            String tname = teacherJdbc.GetTeacherName(homework.getTno());
            homework.setTname(tname);
            if (student_jdbc.QuerySubmit(sno, homework.getH_id())) {
                homework.setIsFinished("已提交");
            }else{
                homework.setIsFinished("未提交");
            }
            homework_list.add(homework);
        }
        request.setAttribute("homework_list", homework_list);

        nextPage = "student";

        return nextPage;
    }

    @RequestMapping("homeworkcheck_student")
    public String Student_Check(@RequestParam("h_id") String h_id,
                                @RequestParam("tname") String tname,
                                HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String sno = (String) request.getSession().getAttribute("sno");
        request.setAttribute("tname",tname);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentJdbc student_jdbc = (StudentJdbc) applicationContext.getBean("student_jdbc");

        Homework homework = student_jdbc.CheckHomework(h_id);
        request.setAttribute("homework",homework);

        Submit sh = student_jdbc.QuerySubmitContent(sno,h_id);
        request.setAttribute("sh",sh);

        nextPage = "homework";

        return nextPage;
    }

    @RequestMapping("homeworksubmit_student")
    public String Student_Submit(@RequestParam("h_id") String h_id,
                                 @RequestParam("commit_time") String commit_time,
                                 @RequestParam("commit_content") String commit_content,
                                 HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String sno = (String) request.getSession().getAttribute("sno");
        String finished = "是";


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Submit sh = (Submit) applicationContext.getBean("submit");
        StudentJdbc student_jdbc = (StudentJdbc) applicationContext.getBean("student_jdbc");
        sh.setH_id(h_id);
        sh.setSno(sno);
        sh.setFinished(finished);
        sh.setCommit_time(commit_time);
        sh.setCommit_content(commit_content);
        student_jdbc.InsertSubmit(sh);
        nextPage = "redirect:/homeworklist_student";
        return nextPage;
    }
}
