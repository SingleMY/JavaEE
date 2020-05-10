package spring.mvc.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import spring.mvc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {

//    @Bean
//    @Scope("prototype")
//    private  static JDBCTemplate getInstance() {
//        return  new JDBCTemplate();
//    }

//    @Autowired
//    private  static JdbcUtil jdbcUtil;


    // 除去select以外的update,delete,insert操作
    public static int opExceptSelect(String sql) {
        int sign = -1;
        Connection connection= null;
        Statement statement = null;
        try {
             connection = JdbcUtil.getConnection();
            // 开启事务，非自动提交
            JdbcUtil.startTransaction();
            //创建可执行语句
            statement = connection.prepareStatement(sql);

            sign =statement.executeUpdate(sql);
            // 提交事务
            JdbcUtil.commit();

        } catch (SQLException e) {
            JdbcUtil.rollback();//事务回退
        }finally {
            // 释放资源，结果集设置为null
            JdbcUtil.release(connection, statement, null);
        }
        return sign;
    }

    // select操作，默认返回获取到的结果集
    public static List<List<Object>> opSelect(String sql, List<Class<?>> cols) {
        List<List<Object>> resultList = new ArrayList<>();
        List<Object> objContent = null;
        Connection connection = null ;
        Statement statement = null;
        try{
            connection = JdbcUtil.getConnection();
            // 开启事务，非自动提交
            JdbcUtil.startTransaction();
            //创建可执行语句
             statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                objContent = new ArrayList<>();
                for (int i = 1; i <= cols.size(); i++) {
                    objContent.add(rs.getObject(i, cols.get(i - 1)));
                }
                resultList.add(objContent);
            }
        } catch (SQLException e) {
            JdbcUtil.rollback();
        }finally {
            JdbcUtil.release(connection,statement,null);
        }

        return resultList;
    }
}
