package com.moyang.homework.dao;

import com.moyang.homework.pojo.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDao extends JpaRepository<Homework,String> {
    @Query(value = "select * from homework where course_no=?1",nativeQuery = true)
    List<Homework> getHomeworkListByCourse(String course_no);

}
