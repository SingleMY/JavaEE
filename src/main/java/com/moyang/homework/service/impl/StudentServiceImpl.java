package com.moyang.homework.service.impl;

import com.moyang.homework.dao.StudentDao;
import com.moyang.homework.pojo.Student;
import com.moyang.homework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: homework
 * @description: 实现学生业务
 * @author: MoYang
 * @create: 2020-05-14 21:29
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    /**
     * 添加
     *
     * @param student
     */
    @Override
    public void insertStudent(Student student) {
        studentDao.save(student);
    }

    /**
     * 删除
     *
     * @param sno
     */
    @Override
    public void deleteStudent(String sno) {

    }

    /**
     * 修改
     *
     * @param student
     */
    @Override
    public void updateStudent(Student student) {

    }

    /**
     * 查询
     *
     * @param course_no
     */
    @Override
    public List<Student> getStudentList(String course_no) {
        return null;
    }

    /**
     * 查询单个
     *
     * @param sno
     */
    @Override
    public Student getStudentBySno(String sno) {
        return null;
    }
}
