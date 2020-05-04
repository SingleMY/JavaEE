package jdbc;


import main.java.Model.Homework;
import main.java.Model.Student;
import main.java.Model.Submit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbc {
    JdbcUtil jdbc_util = new JdbcUtil();

    public boolean Student_login(String sno, String password) {

        PreparedStatement stmt = null;
        Connection conn = null;

        boolean flag=false;
        String sql= "SELECT PASSWORD FROM STUDENT WHERE SNO=(?) ";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();
            // 开启事务，非自动提交
            jdbc_util.startTransaction();
            //创建可执行语句
            stmt = conn.prepareStatement(sql);

            //设置参数
            stmt.setString(1, sno);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()&&rs.getString("password").equals(password)){
                flag=true;
            }else {
                flag=false;
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally{
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return flag;
    }


    public void InsertStudent(Student student) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql= "INSERT INTO STUDENT VALUES (?,?,?)";
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
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }

    }

    public List<Homework> QueryHomework(String sno) {
        PreparedStatement stmt = null;
        Connection conn = null;

        List<Homework> homework_list = new ArrayList<Homework>();
        String sql;
        sql = "SELECT * FROM HOMEWORK WHERE HOMEWORK.TNO IN (SELECT TEACHING.TNO FROM TEACHING WHERE SNO=?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, sno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Homework homework = new Homework();
                homework.setH_id(rs.getString("h_id"));
                homework.setTitle(rs.getString("title"));
                homework.setContent(rs.getString("content"));
                homework.setCreate_time(rs.getString("create_time"));
                homework.setDeadline(rs.getString("deadline"));
                homework.setTno(rs.getString("tno"));
                homework_list.add(homework);
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        } finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return homework_list;
    }

    public boolean QuerySubmit(String sno , String h_id  ) {
        PreparedStatement stmt = null;
        Connection conn = null;

        boolean flag=false;
        String sql;
        sql = "SELECT FINISHED FROM SH WHERE H_ID=? AND SNO=? ";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, h_id);
            stmt.setString(2, sno);
//            stmt.setString(3, student.getSname());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()&&rs.getString("finished").equals("是")) {

                flag=true;
            }else {
                flag=false;
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        } finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
       return  flag;
    }

    public Submit QuerySubmitContent(String sno , String h_id  ) {
        PreparedStatement stmt = null;
        Connection conn = null;

        Submit submit = new Submit();
        String sql;
        sql = "SELECT * FROM SH WHERE H_ID=? AND SNO=? ";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, h_id);
            stmt.setString(2, sno);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                submit.setSno(rs.getString("sno"));
                submit.setH_id(rs.getString("h_id"));
                submit.setFinished(rs.getString("finished"));
                submit.setCommit_time(rs.getString("commit_time"));
                submit.setCommit_content(rs.getString("commit_content"));
            }else {
                submit.setSno(sno);
                submit.setH_id(h_id);
                submit.setFinished("未提交");
                submit.setCommit_time("");
                submit.setCommit_content("");
            }
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
        return submit;
    }

    public void InsertSubmit(Submit sh) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql;
        sql = "INSERT INTO SH VALUES (?,?,?,?,?)";
        try {
            // 获得连接
            conn = jdbc_util.getConnection();

            // 开启事务，非自动提交
            jdbc_util.startTransaction();

            //创建可执行语句
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sh.getH_id());
            stmt.setString(2, sh.getSno());
            stmt.setString(3, sh.getFinished());
            stmt.setString(4, sh.getCommit_time());
            stmt.setString(5,sh.getCommit_content());
            stmt.executeUpdate();
        } catch (SQLException e) {
            jdbc_util.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            jdbc_util.release(conn, stmt, null);
        }
    }
}
