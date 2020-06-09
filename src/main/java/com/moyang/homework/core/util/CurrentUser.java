package com.moyang.homework.core.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: homework
 * @description: 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 * @author: MoYang
 * @create: 2020-05-18 15:39
 **/

@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentUser {
}
