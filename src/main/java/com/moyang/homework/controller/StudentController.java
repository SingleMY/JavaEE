package com.moyang.homework.controller;

import com.moyang.homework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: homework
 * @description: 学生信息请求
 * @author: MoYang
 * @create: 2020-05-18 18:45
 **/
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

}
