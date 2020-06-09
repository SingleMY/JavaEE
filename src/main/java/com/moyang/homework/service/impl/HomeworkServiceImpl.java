package com.moyang.homework.service.impl;

import com.moyang.homework.dao.HomeworkDao;
import com.moyang.homework.pojo.Homework;
import com.moyang.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: homework
 * @description: 作业事务实现
 * @author: MoYang
 * @create: 2020-05-18 18:53
 **/
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDao homeworkDao;

    /**
     * 添加
     *
     * @param homework
     */
    @Override
    public void insertHomework(Homework homework) {

    }

    /**
     * 删除
     *
     * @param h_id
     */
    @Override
    public void deleteHomework(String h_id) {

    }

    /**
     * 修改
     *
     * @param homework
     */
    @Override
    public void updateHomework(Homework homework) {

    }

    /**
     * 查询
     *
     * @param course_no
     */
    @Override
    public List<Homework> getHomeworkListByCourse(String course_no) {

        return homeworkDao.getHomeworkListByCourse(course_no);
    }

    /**
     * 查询单个
     *
     * @param h_id
     */
    @Override
    public Homework getHomeworkByH_id(String h_id) {
        return null;
    }
}
