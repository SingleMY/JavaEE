package com.moyang.homework.service.impl;

import com.moyang.homework.dao.HomeworkDao;
import com.moyang.homework.pojo.Homework;
import com.moyang.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    int homework_num = 0;
    /**
     * 添加
     *
     * @param homework
     */
    @Override
    public void insertHomework(Homework homework) {
        homework.setH_id(String.valueOf(++homework_num));
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tsStr = sdf.format(ts);
        homework.setCreate_time(tsStr);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf1.parse(homework.getDeadline());
            String str = sdf2.format(date);
           homework.setDeadline(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
       homeworkDao.save(homework);
    }

    /**
     * 删除
     *
     * @param h_id
     */
    @Override
    public void deleteHomework(String h_id) {
         homeworkDao.deleteById(h_id);
    }

    /**
     * 修改
     *
     * @param homework
     */
    @Override
    public void updateHomework(Homework homework) {
        homeworkDao.save(homework);
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
