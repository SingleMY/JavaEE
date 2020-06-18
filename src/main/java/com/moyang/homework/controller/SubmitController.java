package com.moyang.homework.controller;

import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.pojo.Homework;
import com.moyang.homework.pojo.Student;
import com.moyang.homework.pojo.Submit;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.SubmitService;
import com.moyang.homework.service.impl.SubmitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: homework
 * @description: 作业提交信息控制
 * @author: MoYang
 * @create: 2020-05-18 19:10
 **/
@RestController
@RequestMapping(value = "/submit")
public class SubmitController {
    @Autowired
    SubmitServiceImpl submitService;

    @LoginRequired
    @PostMapping(value = "/homework")
    public Result submitHomework(@RequestBody Submit submit){
        submitService.insertSubmit(submit);
        return  new Result<Submit>(submit);
    }

    @LoginRequired
    @PostMapping(value = "/listofhomework")
    public Result getSubmitListofHomework(@RequestBody Homework homework){
       List<Submit> submitList = submitService.getSubmitListByHomework(homework.getH_id());
       System.out.println(submitList.toString());
       return  new Result<List<Submit>>(submitList);
    }

    @LoginRequired
    @PostMapping(value = "/listofstudent")
    public Result getSubmitListofStudent(@RequestBody Student student){
        List<Submit> submitList = submitService.getSubmitListByStudent(student.getSno());

        return  new Result<List<Submit>>(submitList);
    }

    @LoginRequired
    @PostMapping(value = "/add")
    public Result addSubmit(@RequestBody Submit submit){

        submitService.addSubmit(submit);
        return  new Result<Submit>(submit);
    }
    @LoginRequired
    @PostMapping(value = "/update")
    public Result updateSubmit(@RequestBody Submit submit){
        submitService.updateSubmit(submit);
        return  new Result<Submit>(submit);
    }
    @LoginRequired
    @PostMapping(value = "/delete")
    public Result deleteSubmit(@RequestBody Submit submit){
        submitService.deleteSubmit(submit);
        return  new Result<Submit>(submit);
    }
}
