package com.moyang.homework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @program: homework
 * @description: 用户
 * @author: MoYang
 * @create: 2020-05-14 17:40
 **/
@Data
@Table(name = "User")
@Entity
//@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String user_id;

    private String username;
    private String password;
    private String avatar;
    private String roles;

}
