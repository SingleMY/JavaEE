package jdbc;


import main.java.Model.Homework;
import main.java.Model.Student;
import main.java.Model.Submit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbc {
    JdbcUtil jdbc_util = new JdbcUtil();

    public boolean Student_login(String sno, String password) {
        jdbc_util.Connect();

        System.out.println(sno);
        boolean flag=false;
        String sql= "SELECT PASSWORD FROM STUDENT WHERE SNO=(?) ";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, sno);
            ResultSet rs = jdbc_util.stmt.executeQuery();
            if(rs.next()&&rs.getString("password").equals(password)){
                flag=true;
            }else {
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();

        return flag;
    }


    public void InsertStudent(Student student) {
        jdbc_util.Connect();

        String sql= "INSERT INTO STUDENT VALUES (?,?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, student.getSno());
            jdbc_util.stmt.setString(2, student.getSname());
            jdbc_util.stmt.setString(3, student.getPassword());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }

    public List<Homework> QueryHomework(String sno) {
        jdbc_util.Connect();

        List<Homework> homework_list = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM HOMEWORK WHERE HOMEWORK.TNO IN (SELECT TEACHING.TNO FROM TEACHING WHERE SNO=?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, sno);
            ResultSet rs = jdbc_util.stmt.executeQuery();
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
            e.printStackTrace();
        }

        jdbc_util.Close();
        return homework_list;
    }

    public boolean QuerySubmit(String sno , String h_id  ) {
        jdbc_util.Connect();

        boolean flag=false;
        String sql;
        sql = "SELECT FINISHED FROM SH WHERE H_ID=? AND SNO=? ";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, h_id);
            jdbc_util.stmt.setString(2, sno);
//            jdbc_util.stmt.setString(3, student.getSname());
            ResultSet rs = jdbc_util.stmt.executeQuery();
            if(rs.next()&&rs.getString("finished").equals("是")) {

                flag=true;
            }else {
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
       return  flag;
    }

    public Submit QuerySubmitContent(String sno , String h_id  ) {
        jdbc_util.Connect();

        Submit submit = new Submit();
        String sql;
        sql = "SELECT * FROM SH WHERE H_ID=? AND SNO=? ";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, h_id);
            jdbc_util.stmt.setString(2, sno);

            ResultSet rs = jdbc_util.stmt.executeQuery();
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
            e.printStackTrace();
        }

        jdbc_util.Close();
        return submit;
    }

    public void InsertSubmit(Submit sh) {
        jdbc_util.Connect();

        String sql;
        sql = "INSERT INTO SH VALUES (?,?,?,?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, sh.getH_id());
            jdbc_util.stmt.setString(2, sh.getSno());
            jdbc_util.stmt.setString(3, sh.getFinished());
            jdbc_util.stmt.setString(4, sh.getCommit_time());
            jdbc_util.stmt.setString(5,sh.getCommit_content());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }
}
