package spring.mvc.dao;

import org.springframework.stereotype.Repository;
import spring.mvc.model.Teacher;
import spring.mvc.util.FieldUtils;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TeacherDao {


    private static TeacherDao teacherDao;
    private static List<Class<?>> teacherFieldClasses;

    private TeacherDao() {}

    public static TeacherDao getInstance() {
        
        if (teacherDao == null) {
            teacherDao = new TeacherDao();
            teacherFieldClasses = new ArrayList<Class<?>>();
            FieldUtils.getClassFields(teacherFieldClasses, Teacher.class);
        }
        return teacherDao;
    }

    public static boolean teacher_login(String tno, String password ) {

        boolean flag = false;
        StringBuilder  sql = new StringBuilder("SELECT * FROM TEACHER WHERE TNO=");
        sql.append(tno).append(" and password=").append(password).append(";");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), teacherFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            flag =false;
        }else {
            flag =true;
        }
        return flag;
    }

    public static void AddTeacher(Teacher teacher) {
        StringBuilder sql = new StringBuilder("insert into teacher values('");
        sql.append(teacher.getTno()).append("','").append(teacher.getTname()).append("','")
                .append(teacher.getPassword()).append("');");

        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String GetTeacherName(String tno){

        StringBuilder sql = new StringBuilder("SELECT TNAME FROM TEACHER WHERE TNO='");
        sql.append(tno).append("';");
        List<List<Object>> resultList = null;
        try {
            resultList =JDBCTemplate.opSelect(sql.toString(),teacherFieldClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {
            String  tname=null;
            for (List<Object> list : resultList) {
               tname = (String) list.get(0);
            }
            return tname;
        }

    }
}
