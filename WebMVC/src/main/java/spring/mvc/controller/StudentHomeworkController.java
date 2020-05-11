package spring.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.dao.HomeworkDao;
import spring.mvc.dao.StudentDao;
import spring.mvc.dao.SubmitDao;
import spring.mvc.dao.TeacherDao;
import spring.mvc.model.Homework;
import spring.mvc.model.Submit;
import spring.mvc.model.Teacher;
import spring.mvc.model.sHomework;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentHomeworkController {

    @Autowired
    HomeworkDao homeworkDao;

    @Autowired
    TeacherDao teacherDao;
    @Autowired
    SubmitDao submitDao;
    @Autowired
    Submit sh;
    @RequestMapping("homeworklist_student")
    public String  Student_Index(HttpServletRequest request){
        String nextPage = "404";
        //处理逻辑
        String sno = (String) request.getSession().getAttribute("sno");

        List<sHomework> homeworkList = homeworkDao.queryHomeworkBySno(sno);

        List<sHomework> homework_list = new ArrayList<>();

        for(sHomework homework: homeworkList) {
            String tname = teacherDao.GetTeacherName(homework.getTno());
            homework.setTname(tname);
            if (submitDao.QuerySubmitContent(homework.getH_id(),sno).getFinished().equals("是")) {
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


        Homework homework = homeworkDao.CheckHomework(h_id);
        request.setAttribute("homework",homework);

        Submit sh = SubmitDao.QuerySubmitContent(sno,h_id);
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

        sh.setH_id(h_id);
        sh.setSno(sno);
        sh.setFinished(finished);
        sh.setCommit_time(commit_time);
        sh.setCommit_content(commit_content);
        submitDao.InsertSubmit(sh);
        nextPage = "redirect:/homeworklist_student";
        return nextPage;
    }
}
