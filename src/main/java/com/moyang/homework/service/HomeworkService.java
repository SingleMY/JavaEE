package com.moyang.homework.service;

import com.moyang.homework.pojo.Homework;

import java.util.List;

public interface HomeworkService {
    /**
     * 添加
     */
    void insertHomework(Homework homework);

    /**
     * 删除
     */
    void deleteHomework(String h_id);

    /**
     * 修改
     */
    void updateHomework(Homework homework);

    /**
     * 查询
     */
    List<Homework> getHomeworkListByCourse(String course_no);

    /**
     * 查询单个
     */
    Homework getHomeworkByH_id(String h_id);
}
