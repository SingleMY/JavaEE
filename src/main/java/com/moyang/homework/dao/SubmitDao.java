package com.moyang.homework.dao;

import com.moyang.homework.pojo.Submit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitDao extends JpaRepository<Submit,String> {

    @Query(value = "select * from submit where h_id=?1",nativeQuery = true)
    List<Submit> getSubmitListByHomework(String h_id);

    @Query(value = "select * from submit where sno=?1",nativeQuery = true)
    List<Submit> getSubmitListByStudent(String sno);
}
