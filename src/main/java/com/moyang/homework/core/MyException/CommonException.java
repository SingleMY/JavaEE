package com.moyang.homework.core.MyException;

/**
 * @program: homework
 * @description: 自定义的异常抛出
 * @author: MoYang
 * @create: 2020-05-18 16:14
 **/
public class CommonException extends Exception{

    public CommonException(int code , String mes){
           super(mes);
    }

}
