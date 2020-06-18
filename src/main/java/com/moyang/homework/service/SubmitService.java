package com.moyang.homework.service;

import com.moyang.homework.pojo.Submit;

import java.util.List;

public interface SubmitService {

    /**
     * 添加
     */
    void insertSubmit(Submit submit);

    void  addSubmit(Submit submit);
    /**
     * 删除
     */
    void deleteSubmit(Submit submit);

    /**
     * 修改
     */
    void updateSubmit(Submit submit);

    /**
     * 查询
     */
    List<Submit> getSubmitListByHomework(String h_id);

    List<Submit> getSubmitListByStudent(String sno);

    /**
     * 查询单个
     */
    Submit getSubmit(String h_id,String sno);
}
