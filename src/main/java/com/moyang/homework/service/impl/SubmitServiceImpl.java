package com.moyang.homework.service.impl;

import com.moyang.homework.dao.SubmitDao;
import com.moyang.homework.pojo.Submit;
import com.moyang.homework.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: homework
 * @description: 作业提交事务实现
 * @author: MoYang
 * @create: 2020-05-18 19:06
 **/
@Service
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    SubmitDao submitDao;
    /**
     * 添加
     *
     * @param submit
     */
    int submit_num = 0;
    @Override
    public void insertSubmit(Submit submit) {
        submit.setSubmit_id(String.valueOf(++submit_num));
        submit.setFinished("已完成");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tsStr = sdf.format(ts);
        submit.setCommit_time(tsStr);
        submitDao.save(submit);
    }

    @Override
    public void addSubmit(Submit submit) {
        submitDao.save(submit);
    }

    /**
     * 删除
     *
     * @param submit
     */
    @Override
    public void deleteSubmit(Submit submit) {
       submitDao.delete(submit);
    }

    /**
     * 修改
     *
     * @param submit
     */
    @Override
    public void updateSubmit(Submit submit) {
      submitDao.save(submit);
    }

    /**
     * 查询
     *
     * @param h_id
     */
    @Override
    public List<Submit> getSubmitListByHomework(String h_id) {
        return submitDao.getSubmitListByHomework(h_id);
    }

    @Override
    public List<Submit> getSubmitListByStudent(String sno) {
        //System.out.println(sno);
        return  submitDao.getSubmitListByStudent(sno);
    }

    /**
     * 查询单个
     *
     * @param h_id
     * @param sno
     */
    @Override
    public Submit getSubmit(String h_id, String sno) {
        return null;
    }
}
