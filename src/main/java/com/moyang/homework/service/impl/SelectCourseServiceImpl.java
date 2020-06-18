package com.moyang.homework.service.impl;

import com.moyang.homework.dao.SelectCourseDao;
import com.moyang.homework.pojo.SelectCourse;
import com.moyang.homework.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: homework
 * @description: 选课事务实现
 * @author: MoYang
 * @create: 2020-05-18 19:07
 **/
@Service
public class SelectCourseServiceImpl implements SelectCourseService {
    @Autowired
    SelectCourseDao selectCourseDao;

    int sc_num = 0;
    /**
     * 添加
     *
     * @param sc
     */
    @Override
    public void addSelectCourse(SelectCourse sc) {
        sc.setSc(String.valueOf(++sc_num));
        selectCourseDao.save(sc);
    }

    /**
     * 删除
     *
     * @param sc
     */
    @Override
    public void deleteSelectCourse(SelectCourse sc) {
       selectCourseDao.deleteById( selectCourseDao.getScByCourseStudent(sc.getCourse_no(),sc.getSno()));
    }
}
