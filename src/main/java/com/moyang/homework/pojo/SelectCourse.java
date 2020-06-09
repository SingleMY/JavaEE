package com.moyang.homework.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: homework
 * @description: 选课表
 * @author: MoYang
 * @create: 2020-05-14 19:53
 **/
@Data
@Table(name = "select_course")
public class SelectCourse {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String course_no;
    @Id
    private String sno;
}
