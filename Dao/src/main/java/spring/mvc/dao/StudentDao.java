package spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.mvc.model.Student;
import spring.mvc.util.FieldUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {
//    @Autowired
//    private  static  JDBCTemplate jdbcTemplate;

    @Autowired
    Student student ;

    private static StudentDao studentDao;
    private static List<Class<?>> studentFieldClasses;

    private StudentDao() {}

    public static StudentDao getInstance() {
        if (studentDao == null) {
            studentDao = new StudentDao();
            studentFieldClasses = new ArrayList<Class<?>>();
            FieldUtils.getClassFields(studentFieldClasses, Student.class);
        }
        return studentDao;
    }

    public boolean student_login(String sno, String password ,String identify) {

        boolean flag = false;
        StringBuilder  sql = new StringBuilder("SELECT * FROM STUDENT WHERE SNO=");
        sql.append(sno).append(" and password=").append(password).append(";");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), studentFieldClasses);

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
    // 插入学生信息
    public void insertStudent(Student student) {
        StringBuilder sql = new StringBuilder("insert into student values('");
        sql.append(student.getSno()).append("','").append(student.getSname()).append("','")
                .append(student.getPassword()).append("');");

        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> QueryStudent(String tno) {


        StringBuilder sql = new StringBuilder("SELECT * FROM STUDENT WHERE STUDENT.SNO IN (SELECT  TEACHING.SNO FROM TEACHING WHERE  TNO=");
        sql.append(tno).append(");");
        List<List<Object>> resultList = null;
        try {
            resultList = JDBCTemplate.opSelect(sql.toString(), studentFieldClasses);

        }catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() <= 0) {
            return null;
        }else {
            List<Student> students = new ArrayList<>();
            for (List<Object> list : resultList) {
                student.setSno((String) list.get(0));
                student.setSname((String) list.get(1));
                student.setPassword((String) list.get(2));
                students.add(student);
            }
            return students;
        }
    }

    public void DeleteStudent( String sno) {


//        sql = "DELETE FROM TEACHING WHERE SNO=? AND TNO=?";

        StringBuilder sql = new StringBuilder("DELETE FROM STUDENT WHERE SNO='");
        sql.append(sno).append("';");

        try {
            JDBCTemplate.opExceptSelect(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
