package com.moyang.homework.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: homework
 * @description: 选课表
 * @author: MoYang
 * @create: 2020-05-14 19:53
 **/
@Data
@Table(name = "select_course")
@Entity
public class SelectCourse {
    @Id
    private  String  sc;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String course_no;

    private String sno;
}
