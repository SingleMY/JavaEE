package com.moyang.homework.result;

import lombok.Data;

/**
 * @program: homework
 * @description: 服务器响应结果:1**需要用户继续操作，2**成功，3**重定向，4**客户端请求错误，5**服务器内部错误
 * @author: MoYang
 * @create: 2020-05-14 17:35
 **/
@Data
public class Result<T> {
    private T data;
    private int code;
    private String msg;

    /**
     * 若没有数据返回，默认状态码为 500，提示信息为“操作无效！”
     */
    public Result() {
        this.code = 500;
        this.msg = "操作无效！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 200，默认提示信息为“操作成功！”
     * @param data
     */
    public Result(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 200，人为指定提示信息
     * @param data
     * @param msg
     */
    public Result(T data, String msg) {
        this.data = data;
        this.code = 200;
        this.msg = msg;
    }
}
