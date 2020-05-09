package jdbc;

import Bean.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherJdbc {
    JdbcUtil jdbc_util = new JdbcUtil();

    public boolean Teacher_login(String sno, String password) {
        PreparedStatement stmt = null;
        Connection conn = null;

        boolean flag=false;
        String sql= "SELECT PASSWORD FROM TEACHER WHERE TNO=(?) ";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();
            // 开启事务，非自动提交
            jdbc_util.startTransaction();
            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sno);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()&&rs.getString("password").equals(password)){
                flag=true;
            }else {
                flag=false;
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }

        return flag;
    }
    public void AddTeacher(Teacher teacher) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql;
        sql = "INSERT INTO TEACHER VALUES (?,?,?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getTno());
            stmt.setString(2, teacher.getTname());
            stmt.setString(3, teacher.getPassword());
            stmt.executeUpdate();
            // 提交事务
            jdbc_util.commit();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        } finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
    }

    public List<sHomework> QueryHomework(String tno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        List<sHomework> shomework_list = new ArrayList<sHomework>();
        String sql;
        sql = "SELECT * FROM HOMEWORK WHERE TNO=?";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tno);
            ResultSet rs = stmt.executeQuery();
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            while (rs.next()) {
                sHomework shomework = (sHomework) applicationContext.getBean("shomework");
                shomework.setH_id(rs.getString("h_id"));
                shomework.setTitle(rs.getString("title"));
                shomework.setContent(rs.getString("content"));
                shomework.setCreate_time(rs.getString("create_time"));
                shomework.setDeadline(rs.getString("deadline"));
                shomework.setTno(rs.getString("tno"));
                shomework_list.add(shomework);
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return shomework_list;
    }

    public void AddHomework(Homework homework) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql;
        sql = "INSERT INTO HOMEWORK VALUES (?,?,?,?,?,?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, homework.getH_id());
            stmt.setString(2, homework.getTitle());
            stmt.setString(3, homework.getContent());
            stmt.setString(4, homework.getCreate_time());
            stmt.setString(5, homework.getDeadline());
            stmt.setString(6, homework.getTno());
            stmt.executeUpdate();

            // 提交事务
            jdbc_util.commit();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
    }

    public void AddStudent(Student student , String tno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql;

        sql = "INSERT INTO STUDENT VALUES (?,?,?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getSno());
            stmt.setString(2, student.getSname());
            stmt.setString(3, student.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }

        sql = "INSERT INTO TEACHING VALUES (?,?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getSno());
            stmt.setString(2, tno);
            stmt.executeUpdate();
            // 提交事务
            jdbc_util.commit();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        } finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
    }

    public List<Student> QueryStudent(String tno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        List<Student> student_list = new ArrayList<Student>();

        String sql;
        sql = "SELECT * FROM STUDENT WHERE STUDENT.SNO IN (SELECT  TEACHING.SNO FROM TEACHING WHERE  TNO=?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tno);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString("sno"));
                student.setSname(rs.getString("sname"));
                student_list.add(student);
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return student_list;
    }

    public List<Submit> QuerySubmit(String h_id, String tno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        List<Submit> submit_list = new ArrayList<Submit>();

        String sql;
        sql = "SELECT * FROM SH WHERE H_ID=? and SH.SNO IN ( SELECT TEACHING.SNO FROM  TEACHING  WHERE TNO=?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, h_id);
            stmt.setString(2, tno);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
                Submit submit = (Submit) applicationContext.getBean("submit");
                submit.setH_id(rs.getString("h_id"));
                submit.setSno(rs.getString("sno"));
                submit.setFinished(rs.getString("finished"));
                submit.setCommit_time(rs.getString("commit_time"));
                submit.setCommit_content(rs.getString("commit_content"));
                submit_list.add(submit);
            }

        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return submit_list;
    }

    public String CheckContent(String h_id, String sno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String content = null;

        String sql;
        sql = "SELECT COMMIT_CONTENT FROM SH WHERE HOMEWORK_TITLE=? and TEACHER_NAME=? and STUDENT_NAME=?";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, homework_title);
//            stmt.setString(2, teacher.getTeacher_name());
//            stmt.setString(3, student_name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                content = rs.getString(1);
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }

        finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return content;
    }

    public String GetTeacherName(String tno){

        PreparedStatement stmt = null;
        Connection conn = null;

        String tname=null;
        String sql;
        sql = "SELECT TNAME FROM TEACHER WHERE TNO=?";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tname = rs.getString("tname");
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }

        finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return tname;

    }

    public void DeleteStudent(String tno, String sno) {
        PreparedStatement stmt = null;
        Connection conn = null;


        String sql;
        sql = "DELETE FROM TEACHING WHERE SNO=? AND TNO=?";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sno);
            stmt.setString(2, tno);
            stmt.executeUpdate();
            // 提交事务
            jdbc_util.commit();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }
        sql = "DELETE FROM STUDENT WHERE SNO=?";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }

        finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
    }
}
