package com.moyang.homework.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: homework
 * @description: 老师
 * @author: MoYang
 * @create: 2020-05-14 19:45
 **/
@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String tno;
    private String tname;
    private String email;
    private String tel;
    private String office;
}
