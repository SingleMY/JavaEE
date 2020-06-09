package com.moyang.homework.dao;

import com.moyang.homework.pojo.SelectCourse;
import com.moyang.homework.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * @program: homework
 * @description: 用户操作持久层
 * @author: MoYang
 * @create: 2020-05-14 21:36
 **/
@Repository
public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "select * from user where user_id=?1",nativeQuery = true)
    User findByUser_id(String user_id);

    @Query(value = "select * from user where user_id=?1 and password =?2",nativeQuery = true)
    User getByUser_idAndpassword(String user_id,String password);
}



