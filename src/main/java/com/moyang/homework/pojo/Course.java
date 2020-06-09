package com.moyang.homework.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: homework
 * @description: 课程
 * @author: MoYang
 * @create: 2020-05-14 19:47
 **/
@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String course_no;

    private String course_name;
    private String course_room;
    private String course_time;
    private String tno;
    private String course_notice;

}
