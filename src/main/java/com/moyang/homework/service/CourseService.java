package com.moyang.homework.service;

import com.moyang.homework.pojo.Course;

import java.util.List;

public interface CourseService {
    /**
     * 添加
     */
    void insertCourse(Course course);

    /**
     * 删除
     */
    void deleteCourse(String course_no);

    /**
     * 修改
     */
    void updateCourse(Course course);

    /**
     * 老师查询
     */
    List<Course> getCourseListByTno(String tno);

    /**
     * 学生查询
     */
    List<Course> getCourseListBySno(String sno);

    /**
     * 查询单个
     */
    Course getCourseByNo(String course_no);
}
