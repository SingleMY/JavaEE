package com.moyang.homework.service;

import com.moyang.homework.pojo.Student;
import com.moyang.homework.pojo.User;

/**
 * @program: homework
 * @description: 用户业务
 * @author: MoYang
 * @create: 2020-05-14 20:59
 **/
public interface UserService {


    /**
     * 确定用户账号是否已经存在
     *
     * @param user_id
     * @return boolean
     */
    boolean isExist(String user_id);

    User getByuser_id(String user_id);

    User get(String user_id, String password);

    void adduser(User user);


}
