package com.moyang.homework.service.impl;

import com.moyang.homework.dao.StudentDao;
import com.moyang.homework.dao.TeacherDao;
import com.moyang.homework.dao.UserDao;
import com.moyang.homework.pojo.Student;
import com.moyang.homework.pojo.Teacher;
import com.moyang.homework.pojo.User;
import com.moyang.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: homework
 * @description: 用户业务实现
 * @author: MoYang
 * @create: 2020-05-14 21:30
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Override
    public boolean isExist(String user_id) {
        User user = getByuser_id(user_id);
        return null!=user;
    }

    @Override
    public User getByuser_id(String user_id) {
        return userDao.findByUser_id(user_id);
    }

    @Override
    public User get(String user_id, String password) {
        return userDao.getByUser_idAndpassword(user_id,password);
    }

    @Override
    public void adduser(User user) {
        if(user.getRoles()== "student"){
            Student student = new Student();
            student.setSno(user.getUser_id());
            student.setSname(user.getUsername());
            studentDao.save(student);
        }else{
            Teacher teacher = new Teacher();
            teacher.setTno(user.getUser_id());
            teacher.setTname(user.getUsername());
            teacherDao.save(teacher);
        }
        userDao.save(user);
    }


}
