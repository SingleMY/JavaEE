package com.moyang.homework.dao;

import com.moyang.homework.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao  extends JpaRepository<Teacher,String> {
}
