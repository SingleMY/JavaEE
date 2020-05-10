package spring.mvc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 *用于获取数据库连接对象的工具类。
 */
@Configuration
public class JdbcUtil {
    @Resource(name = "datasource")
    private static DataSource dataSource;

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static final Object object = new Object();
    private static final Logger log = LoggerFactory.getLogger(JdbcUtil.class);


//    @Bean
//    @Scope("prototype")
//    public static JdbcUtil getInstance(){
//        return  new JdbcUtil();
//    }

    /**
     *获取数据库连接对象的方法，线程安全
     */
    public static  Connection getConnection() throws SQLException {
        // 从当前线程中获取连接对象
        Connection connection = tl.get();
        // 判断为空的话，创建连接并绑定到当前线程
        if(connection == null) {
            synchronized(object) {
                if(tl.get() == null) {
                    connection = createConnection();
                    tl.set(connection);
                }
            }
        }
        return connection;
    }


    /**
     *创建数据库连接
     */
    private  static  Connection createConnection() throws SQLException {
        if(dataSource == null) {
            throw new RuntimeException("创建数据源失败");
        }
        Connection conn = null;
        // 获得连接
        conn = dataSource.getConnection();
        return conn;
    }

    /**
     *释放资源
     */
    public static void release(Connection conn, Statement statement, ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch(SQLException e) {
                log.error("关闭ResultSet对象异常", e);
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch(SQLException e) {
                log.error("关闭Statement对象异常", e);
            }
        }
        // 注意：这里不关闭连接
        if(conn != null) {
            try {
                conn.close();
                tl.remove();
            } catch(SQLException e) {
                log.error("关闭Connection对象异常", e);
            }
        }
    }

    /**
     *开启事务
     */
   public static  void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     *提交事务
     */
    public static  void commit() {
        Connection connection = tl.get();
        if(connection != null) {
            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch(SQLException e) {
                log.error("提交事务失败", e);
            }
        }
    }

    /**
     *回滚事务
     */
    public static  void rollback() {
        Connection connection = tl.get();
        if(connection != null) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch(SQLException e) {
                log.error("回滚事务失败", e);
            }
        }
    }

    public static  DataSource getDataSource() {
        return dataSource;
    }

    public static  void setDataSource(DataSource dataSource) {
       JdbcUtil.dataSource = dataSource;
        tl.remove();
    }



}