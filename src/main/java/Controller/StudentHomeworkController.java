package Controller;

import jdbc.StudentJdbc;
import jdbc.TeacherJdbc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentHomeworkController {
    @RequestMapping("homeworklist_student")
    public String  Student_Index(HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String sno = (String) request.getSession().getAttribute("sno");

        StudentJdbc student_jdbc = new StudentJdbc();
        List<Model.sHomework> homeworkList = student_jdbc.QueryHomework(sno);


        List<Model.sHomework> homework_list = new ArrayList<>();
        TeacherJdbc teacherJdbc = new TeacherJdbc();
        for(Model.sHomework homework: homeworkList) {
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

        StudentJdbc student_jdbc = new StudentJdbc();

        Model.Homework homework = student_jdbc.CheckHomework(h_id);
        request.setAttribute("homework",homework);

        main.java.Model.Submit sh = student_jdbc.QuerySubmitContent(sno,h_id);
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
        StudentJdbc student_jdbc = new StudentJdbc();
        main.java.Model.Submit sh = new main.java.Model.Submit(h_id,sno,finished,commit_time,commit_content);
        student_jdbc.InsertSubmit(sh);
        nextPage = "redirect:/homeworklist_student";

        return nextPage;
    }
}
