package Controller;

import Bean.Homework;
import Bean.Teacher;
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
public class TeacherHomeworkController {
    @RequestMapping("homeworklist_teacher")
    public String  Teacher_Index(HttpServletRequest request){

        String nextPage = "404";
        //处理逻辑
        String tno = (String) request.getSession().getAttribute("tno");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeacherJdbc teacher_jdbc = (TeacherJdbc) applicationContext.getBean("teacher_jdbc");

        List<sHomework> homeworkList = teacher_jdbc.QueryHomework(tno);
        List<sHomework> shomeworkList = new ArrayList<sHomework>();
        for(sHomework homework: homeworkList) {

           int  count= teacher_jdbc.QuerySubmit(homework.getH_id(),tno).size();

            homework.setCount(count);

            shomeworkList.add(homework);
        }
        request.setAttribute("shomework_list", shomeworkList);

        nextPage = "teacher";

        return nextPage;
    }

    @RequestMapping("studentlist")
    public  String  Student_list(HttpServletRequest request){
     String nextPage=null;
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
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
