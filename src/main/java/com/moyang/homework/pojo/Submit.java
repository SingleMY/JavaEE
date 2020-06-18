package com.moyang.homework.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: homework
 * @description: 作业提交
 * @author: MoYang
 * @create: 2020-05-14 19:49
 **/

@Data
@Table(name = "submit")
@Entity
public class Submit {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String submit_id;

    private String h_id;
    private String sno;
    private String finished;
    private String commit_time;
    private String commit_content;
    @Override
    public int hashCode() {
        return Objects.hash(sno, h_id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Submit)) {
            return false;
        }

        Submit sh = (Submit) object;
        return Objects.equals(h_id, sh.h_id )&&Objects.equals(sno, sh.sno );
    }


}
