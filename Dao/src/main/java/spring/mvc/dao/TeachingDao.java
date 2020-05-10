package spring.mvc.dao;

import org.springframework.stereotype.Repository;
import spring.mvc.model.Student;
import spring.mvc.model.Teacher;
import spring.mvc.util.FieldUtils;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TeachingDao {

    private static TeachingDao teachingDao;
    private static List<Class<?>> teachingFieldClasses;
    

    public static TeachingDao getInstance() {

        if (teachingDao == null) {
            teachingDao = new TeachingDao();
            teachingFieldClasses = new ArrayList<Class<?>>();
            FieldUtils.getClassFields(teachingFieldClasses, Teacher.class);
        }
        return teachingDao;
    }
    public void insertSh(Student student , String tno) {

        StringBuilder sql = new StringBuilder("insert into teaching values('");
        sql.append(student.getSno()).append("','").append(tno).append("','")
                .append("');");

        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
