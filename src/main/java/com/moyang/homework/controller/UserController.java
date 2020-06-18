package com.moyang.homework.controller;

import com.moyang.homework.core.interceptors.auth.CurrentUserConstants;
import com.moyang.homework.core.util.CurrentUser;
import com.moyang.homework.core.util.LoginRequired;
import com.moyang.homework.core.util.TokenUtils;
import com.moyang.homework.pojo.User;
import com.moyang.homework.result.Result;
import com.moyang.homework.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


/**
 * @program: homework
 * @description: 用户登录注册等
 * @author: MoYang
 * @create: 2020-05-14 17:14
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/login")
    public Result login( @RequestBody User reqUser) {


        String user_id= reqUser.getUser_id();
        String password=reqUser.getPassword();
        if (userService.getByuser_id(user_id)== null){
            return new Result<>(400,"用户不存在！");
        }else {
            User user = userService.get(user_id,password);
            if (user == null ){
                return new Result<>(400,"密码错误！");
            }else{
                String accessToken = TokenUtils.createJwtToken(user);
                return new Result<>(accessToken ,"登陆成功！");
            }
        }
    }
@ResponseBody
@LoginRequired
@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
public Result getInfo(HttpServletRequest request) {

        User user = (User)request.getAttribute(CurrentUserConstants.CURRENT_USER);
        user.setPassword("");

        return new Result<User>(user);

}

    @PostMapping("/register")
    public Result register(@RequestBody User reqUser) {
        //判断用户是否存在
        if (userService.isExist(reqUser.getUser_id())){
            return new Result<>(400,"用户已存在！");
        }else {
            userService.adduser(reqUser);
            return  new Result(200,"注册成功");
        }
    }

    @PostMapping(value = "/logout")
    public Result logout( HttpServletRequest request) {

        request.setAttribute(CurrentUserConstants.CURRENT_USER, null);

        return  new Result(200,"登出成功");
    }

}
