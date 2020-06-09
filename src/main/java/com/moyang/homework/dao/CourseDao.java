package com.moyang.homework.dao;

import com.moyang.homework.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository<Course,String> {
    @Query(value = "select * from course where course_no=?1",nativeQuery = true)
    Course findByCourse_no(String course_no);

    @Query(value = "select * from course where course_no in (select course_no from select_course where sno =?1)",nativeQuery = true)
    List<Course> findCoursesBySno(String sno);

    @Query(value = "select * from course where tno = ?1",nativeQuery = true)
    List<Course> findCoursesByTno(String tno);

}
