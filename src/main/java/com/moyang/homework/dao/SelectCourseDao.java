package com.moyang.homework.dao;

import com.moyang.homework.pojo.SelectCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectCourseDao extends JpaRepository<SelectCourse,String> {
    @Query(value = "select sc from select_course where course_no=?1 and sno=?2",nativeQuery = true)
    String  getScByCourseStudent(String course_no , String sno);
}
