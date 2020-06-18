package com.moyang.homework.controller;

import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.pojo.SelectCourse;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.impl.SelectCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: homework
 * @description: 课程选择信息控制
 * @author: MoYang
 * @create: 2020-05-18 19:18
 **/
@RestController
@RequestMapping(value = "/sc")
public class SelectCourseController {
    @Autowired
    SelectCourseServiceImpl selectCourseService;

    @LoginRequired
    @PostMapping(value = "/add")
    public Result addSelectCourse(@RequestBody SelectCourse sc){

        selectCourseService.addSelectCourse(sc);
        return  new Result<SelectCourse>(sc);
    }

    @LoginRequired
    @PostMapping(value = "/delete")
    public Result deleteSelectCourse(@RequestBody SelectCourse sc){

        selectCourseService.deleteSelectCourse(sc);
        return  new Result<SelectCourse>(sc);
    }
}
