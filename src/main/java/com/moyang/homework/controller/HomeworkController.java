package com.moyang.homework.controller;

import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.pojo.Course;
import com.moyang.homework.pojo.Homework;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.impl.HomeworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: homework
 * @description: 作业信息控制
 * @author: MoYang
 * @create: 2020-05-18 19:09
 **/
@RestController
@RequestMapping(value = "/homework")
public class HomeworkController {

    @Autowired
    HomeworkServiceImpl homeworkService;

    @LoginRequired
    //@ResponseBody
    @PostMapping(value = "/listofcourse")
    public Result getHomeworkListByCourse(@RequestBody Course course ){

        List<Homework> homeworkList = homeworkService.getHomeworkListByCourse(course.getCourse_no());
        return  new Result<List<Homework>>(homeworkList);
    }

    @LoginRequired
    @PostMapping(value = "/add")
    public Result addHomework(@RequestBody Homework homework){

        homeworkService.insertHomework(homework);
        return  new Result<Homework>(homework);
    }

    @LoginRequired
    @PostMapping(value = "/update")
    public Result updateHomework(@RequestBody Homework homework){

        homeworkService.updateHomework(homework);
        return  new Result<Homework>(homework);
    }

    @LoginRequired
    @PostMapping(value = "/delete")
    public Result deleteHomework(@RequestBody Homework homework){
        homeworkService.deleteHomework(homework.getH_id());
        return  new Result<Homework>(homework);
    }
}
