package com.moyang.homework.controller;

import com.moyang.homework.core.interceptors.auth.CurrentUserConstants;
import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.pojo.Course;
import com.moyang.homework.pojo.Student;
import com.moyang.homework.pojo.User;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @LoginRequired
    @GetMapping(value = "/all")
    public Result getAllStudent(){
        List<Student> studentList = studentService.getAllStudent();
        return new Result<List<Student>>(studentList);
    }

    @LoginRequired
    @PostMapping(value = "/listofcourse")
    public Result getStudentListOfCourse(@RequestBody Course course){
       List<Student> studentList = studentService.getStudentList(course.getCourse_no());
        return new Result<List<Student>>(studentList);
    }

    @LoginRequired
    @PostMapping(value = "/unlistofcourse")
    public Result getunStudentListOfCourse(@RequestBody Course course){
        List<Student> studentList = studentService.getunStudentList(course.getCourse_no());
        return new Result<List<Student>>(studentList);
    }
    @LoginRequired
    @PostMapping(value = "/add")
    public Result addStudent(@RequestBody Student student){
        studentService.insertStudent(student);
        return new Result<Student>(student);
    }

    @LoginRequired
    @PostMapping(value = "/update")
    public Result updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return new Result<Student>(student);
    }

    @LoginRequired
    @PostMapping(value = "/delete")
    public Result deleteStudent(@RequestBody Student student){
        studentService.deleteStudent(student.getSno());
        return new Result<Student>(student);
    }

    @LoginRequired
    @PostMapping(value = "/infor")
    public Result selectStudent(HttpServletRequest request){
        User user = (User) request.getAttribute(CurrentUserConstants.CURRENT_USER) ;
        Student student = studentService.getStudentBySno(user.getUser_id());
        return new Result<Student>(student);
    }
}
