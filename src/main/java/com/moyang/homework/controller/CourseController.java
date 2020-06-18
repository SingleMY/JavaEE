package com.moyang.homework.controller;

import com.moyang.homework.core.interceptors.auth.CurrentUserConstants;
import com.moyang.homework.core.util.CurrentUser;
import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.pojo.Course;
import com.moyang.homework.pojo.User;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: homework
 * @description: 课程信息控制
 * @author: MoYang
 * @create: 2020-05-18 19:09
 **/
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;


    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "/listofstudent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result getCourseListBySno( HttpServletRequest request){
        User user = (User) request.getAttribute(CurrentUserConstants.CURRENT_USER) ;
        List<Course> courseList = courseService.getCourseListBySno(user.getUser_id());
        return  new Result<List<Course>>(courseList);
    }

    @LoginRequired
    @GetMapping(value = "/listofteacher")
    @ResponseBody
    public Result getCourseListByTno(HttpServletRequest request){
        User user = (User) request.getAttribute(CurrentUserConstants.CURRENT_USER) ;
        List<Course> courseList = courseService.getCourseListByTno(user.getUser_id());
        return  new Result<List<Course>>(courseList);
    }

    @LoginRequired
    @PostMapping(value = "/add")
    @ResponseBody
    public Result addCourse(@RequestBody Course course){
        courseService.insertCourse(course);
        return  new Result<Course>(course);
    }

    @LoginRequired
    @PostMapping(value = "/update")
    public Result updateCourse(@RequestBody Course course){

        courseService.updateCourse(course);
        return  new Result<Course>(course);
    }

    @LoginRequired
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result deleteCourse(@RequestBody Course course){

        courseService.deleteCourse(course.getCourse_no());
        return  new Result<Course>(course);
    }
}
