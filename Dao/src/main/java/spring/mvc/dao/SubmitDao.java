package spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import spring.mvc.model.Submit;
import spring.mvc.util.FieldUtils;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SubmitDao {

    @Autowired
    Submit submit;
    private static SubmitDao submitDao;
    private static List<Class<?>> submitFieldClasses;

    public static SubmitDao getInstance() {

        if (submitDao == null) {
            submitDao = new SubmitDao();
            submitFieldClasses = new ArrayList<Class<?>>();
            FieldUtils.getClassFields(submitFieldClasses, Submit.class);
        }
        return submitDao;
    }

    public void InsertSubmit(Submit sh) {


        StringBuilder sql = new StringBuilder("insert into sh values('");
        sql.append(sh.getSno()).append("','").append(sh.getH_id()).append("','")
                .append(sh.getFinished()).append("','")
                .append(sh.getCommit_time()).append("',").append(sh.getCommit_content()).append(");");
        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Submit QuerySubmitContent(String sno , String h_id  ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM SH WHERE H_ID= ");
        sql.append(h_id).append(" and sno= ").append(sno).append(";");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), submitFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {

            for (List<Object> list : resultList) {
                submit.setSno((String) list.get(0));
                submit.setH_id((String) list.get(1));
                submit.setFinished((String) list.get(2));
                submit.setCommit_time((String)list.get(3));
                submit.setCommit_content((String) list.get(4));
            }
            return submit;
        }

    }

    public List<Submit> QuerySubmit(String h_id, String tno) {

        StringBuilder sql = new StringBuilder("SELECT * FROM SH WHERE H_ID= ");
        sql.append(h_id).append(" and SH.SNO IN ( SELECT TEACHING.SNO FROM  TEACHING  WHERE TNO= ").append(tno).append(");");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), submitFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {
            List<Submit> submits = new ArrayList<>();
            for (List<Object> list : resultList) {
                submit.setSno((String) list.get(0));
                submit.setH_id((String) list.get(1));
                submit.setFinished((String) list.get(2));
                submit.setCommit_time((String)list.get(3));
                submit.setCommit_content((String) list.get(4));
                submits.add(submit);
            }
            return submits;
        }
    }

}
