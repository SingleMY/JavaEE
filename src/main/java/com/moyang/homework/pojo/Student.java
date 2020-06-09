package com.moyang.homework.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: homework
 * @description: 学生
 * @author: MoYang
 * @create: 2020-05-14 19:44
 **/
@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String sno;
    private String sname;
    private int age;
    private String tel;

    @Override
    public int hashCode() {
        return Objects.hashCode(sno);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Student)) {
            return false;
        }

        Student student = (Student) object;
        return Objects.equals(sno, student.sno);
    }
}