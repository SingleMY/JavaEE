package com.moyang.homework.service.impl;

import com.moyang.homework.dao.CourseDao;
import com.moyang.homework.pojo.Course;
import com.moyang.homework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: homework
 * @description: 课程事务实现
 * @author: MoYang
 * @create: 2020-05-18 19:00
 **/
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;
    /**
     * 添加
     *
     * @param course
     */
    @Override
    public void insertCourse(Course course) {
        courseDao.save(course);
    }

    /**
     * 删除
     *
     * @param course_no
     */
    @Override
    public void deleteCourse(String course_no) {
        courseDao.deleteById(course_no);
    }

    /**
     * 修改
     *
     * @param course
     */
    @Override
    public void updateCourse(Course course) {
       courseDao.save(course);
    }

    /**
     * 老师查询
     *
     * @param tno
     */
    @Override
    public List<Course> getCourseListByTno(String tno) {
        List<Course> courseList =courseDao.findCoursesByTno(tno);
        return courseList;
    }

    /**
     * 学生查询
     *
     * @param sno
     */
    @Override
    public List<Course> getCourseListBySno(String sno) {
        List<Course> courseList =courseDao.findCoursesBySno(sno);
        System.out.println(courseList);
        return courseList;
    }

    /**
     * 查询单个
     *
     * @param course_no
     */
    @Override
    public Course getCourseByNo(String course_no) {
        Course course = courseDao.findByCourse_no(course_no);
        return null;
    }
}
