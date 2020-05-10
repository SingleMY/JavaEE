package spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.mvc.model.Homework;
import spring.mvc.model.sHomework;
import spring.mvc.util.FieldUtils;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HomeworkDao {

    @Autowired
    Homework homework;
    @Autowired
    sHomework sHomework;
    private static HomeworkDao homeworkDao;
    private static List<Class<?>> homeworkFieldClasses;

    public static HomeworkDao getInstance() {

        if (homeworkDao == null) {
            homeworkDao = new HomeworkDao();
            homeworkFieldClasses = new ArrayList<Class<?>>();
            FieldUtils.getClassFields(homeworkFieldClasses, Homework.class);
        }
        return homeworkDao;
    }
    public void AddHomework(Homework homework) {


        StringBuilder sql = new StringBuilder("INSERT INTO HOMEWORK VALUES ('");
        sql.append(homework.getH_id()).append("','").append(homework.getTitle()).append("','")
                .append(homework.getContent()).append("','")
                .append(homework.getCreate_time()).append("','")
                .append(homework.getDeadline()).append("',").append(homework.getTno()).append(");");
        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<sHomework> queryHomeworkBySno(String sno) {

        StringBuilder sql = new StringBuilder("SELECT * FROM HOMEWORK WHERE HOMEWORK.TNO IN (SELECT TEACHING.TNO FROM TEACHING WHERE SNO= ");
        sql.append(sno).append(");");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), homeworkFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {
            List<sHomework> shomeworks = new ArrayList<>();
            for (List<Object> list : resultList) {
                sHomework.setH_id((String) list.get(0));
                sHomework.setTitle((String)list.get(1));
                sHomework.setContent((String)list.get(2));
                sHomework.setCreate_time((String)list.get(3));
                sHomework.setDeadline((String)list.get(4));
                sHomework.setTno((String)list.get(5));
                shomeworks.add(sHomework);
            }
            return shomeworks;
        }
    }


    public Homework CheckHomework(String h_id) {

        StringBuilder sql = new StringBuilder("SELECT * FROM HOMEWORK WHERE H_ID= ");
        sql.append(h_id).append(";");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), homeworkFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {

            for (List<Object> list : resultList) {
                homework.setH_id((String) list.get(0));
                homework.setTitle((String)list.get(1));
                homework.setContent((String)list.get(2));
                homework.setCreate_time((String)list.get(3));
                homework.setDeadline((String)list.get(4));
                homework.setTno((String)list.get(5));
            }
            return homework;
        }
    }


    public List<sHomework> queryHomeworkByTno(String tno) {

        StringBuilder sql = new StringBuilder("SELECT * FROM HOMEWORK WHERE TNO= ");
        sql.append(tno).append(");");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), homeworkFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {
            List<sHomework> shomeworks = new ArrayList<>();
            for (List<Object> list : resultList) {
                sHomework.setH_id((String) list.get(0));
                sHomework.setTitle((String)list.get(1));
                sHomework.setContent((String)list.get(2));
                sHomework.setCreate_time((String)list.get(3));
                sHomework.setDeadline((String)list.get(4));
                sHomework.setTno((String)list.get(5));
                shomeworks.add(sHomework);
            }
            return shomeworks;
        }
    }
}
