package com.moyang.homework.pojo;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Objects;
/**
 * @program: homework
 * @description: 作业
 * @author: MoYang
 * @create: 2020-05-14 19:41
 **/
@Data
@Entity
@Table(name = "homework")
public class Homework {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String h_id;
    private String title;
    private String content;
    private Timestamp create_time;
    private Timestamp deadline;
    private String course_no;


    @Override
    public int hashCode() {
        return Objects.hashCode(h_id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Homework)) {
            return false;
        }

        Homework homework = (Homework) object;
        return Objects.equals(h_id, homework.h_id );
    }


}