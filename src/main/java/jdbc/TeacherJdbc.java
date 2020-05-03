package jdbc;

import main.java.Model.Teacher;
import main.java.Model.Homework;
import main.java.Model.Student;
import main.java.Model.Submit;
import main.java.Model.Teach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherJdbc {
    JdbcUtil jdbc_util = new JdbcUtil();

    public boolean Teacher_login(String sno, String password) {
        jdbc_util.Connect();

        boolean flag=false;
        String sql= "SELECT PASSWORD FROM TEACHER WHERE TNO=(?) ";
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
    public void AddTeacher(Teacher teacher) {
        jdbc_util.Connect();

        String sql;
        sql = "INSERT INTO TEACHER VALUES (?,?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, teacher.getTno());
            jdbc_util.stmt.setString(2, teacher.getTname());
            jdbc_util.stmt.setString(3, teacher.getPassword());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }

    public List<Homework> QueryHomework(String tno) {
        jdbc_util.Connect();

        List<Homework> homework_list = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM HOMEWORK WHERE TNO=?";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, tno);
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

    public void AddHomework(Homework homework) {
        jdbc_util.Connect();

        String sql;
        sql = "INSERT INTO HOMEWORK VALUES (?,?,?,?,?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, homework.getH_id());
            jdbc_util.stmt.setString(2, homework.getTitle());
            jdbc_util.stmt.setString(3, homework.getContent());
            jdbc_util.stmt.setString(4, homework.getCreate_time());
            jdbc_util.stmt.setString(5, homework.getDeadline());
            jdbc_util.stmt.setString(6, homework.getTno());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }

    public void AddStudent(Student student , String tno) {
        jdbc_util.Connect();

        String sql;

        sql = "INSERT INTO STUDENT VALUES (?,?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, student.getSno());
            jdbc_util.stmt.setString(2, student.getSname());
            jdbc_util.stmt.setString(3, student.getPassword());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO TEACHING VALUES (?,?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, student.getSno());
            jdbc_util.stmt.setString(2, tno);
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }

    public List<Student> QueryStudent(String tno) {
        jdbc_util.Connect();
        List<Student> student_list = new ArrayList<>();

        String sql;
        sql = "SELECT * FROM STUDENT WHERE STUDENT.SNO IN (SELECT  TEACHING.SNO FROM TEACHING WHERE  TNO=?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, tno);

            ResultSet rs = jdbc_util.stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString("sno"));
                student.setSname(rs.getString("sname"));
                student_list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
        return student_list;
    }

    public List<Submit> QuerySubmit(String h_id, String tno) {
        jdbc_util.Connect();
        List<Submit> submit_list = new ArrayList<>();

        String sql;
        sql = "SELECT * FROM SH WHERE H_ID=? and SH.SNO IN ( SELECT TEACHING.SNO FROM  TEACHING  WHERE TNO=?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, h_id);
            jdbc_util.stmt.setString(2, tno);

            ResultSet rs = jdbc_util.stmt.executeQuery();
            while (rs.next()) {
                Submit submit = new Submit();
                submit.setH_id(rs.getString("h_id"));
                submit.setSno(rs.getString("sno"));
                submit.setFinished(rs.getString("finished"));
                submit.setCommit_time(rs.getString("commit_time"));
                submit.setCommit_content(rs.getString("commit_content"));
                submit_list.add(submit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
        return submit_list;
    }

    public String CheckContent(String h_id, String sno) {
        jdbc_util.Connect();
        String content = null;

        String sql;
        sql = "SELECT COMMIT_CONTENT FROM SH WHERE HOMEWORK_TITLE=? and TEACHER_NAME=? and STUDENT_NAME=?";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
//            jdbc_util.stmt.setString(1, homework_title);
//            jdbc_util.stmt.setString(2, teacher.getTeacher_name());
//            jdbc_util.stmt.setString(3, student_name);
            ResultSet rs = jdbc_util.stmt.executeQuery();
            if(rs.next()) {
                content = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
        return content;
    }

    public String GetTeacherName(String tno){

        jdbc_util.Connect();
        String tname=null;
        String sql;
        sql = "SELECT TNAME FROM TEACHER WHERE TNO=?";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, tno);
            ResultSet rs = jdbc_util.stmt.executeQuery();
            while (rs.next()) {
                tname = rs.getString("tname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
        return tname;

    }

    public void DeleteStudent(String tno, String sno) {
        jdbc_util.Connect();

        String sql;
        sql = "DELETE FROM TEACHING WHERE SNO=? AND TNO=?";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, sno);
            jdbc_util.stmt.setString(2, tno);
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "DELETE FROM STUDENT WHERE SNO=?";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, sno);
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbc_util.Close();
    }
}
