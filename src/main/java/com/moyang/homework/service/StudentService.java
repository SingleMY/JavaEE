package com.moyang.homework.service;

import com.moyang.homework.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * 添加
     */
    void insertStudent(Student student);

    /**
     * 删除
     */
    void deleteStudent(String sno);

    /**
     * 修改
     */
    void updateStudent(Student student);

    /**
     * 查询
     */
    List<Student> getStudentList(String course_no);

    /**
     * 查询单个
     */
    Student getStudentBySno(String sno);

}
