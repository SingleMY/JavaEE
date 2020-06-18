package com.moyang.homework.dao;

import com.moyang.homework.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student,String> {
    @Query(value = "select * from student where student.sno in (select select_course.sno from select_course where course_no=?1)",nativeQuery = true)
    List<Student> getStudentListByCourse(String course_no);

    @Query(value = "select * from student where student.sno not in (select select_course.sno from select_course where course_no=?1)",nativeQuery = true)
    List<Student> getunStudentListByCourse(String course_no);
}
