package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *用于获取数据库连接对象的工具类。
 */
public class JdbcUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static final Object obj = new Object();
    private static final Logger log = LoggerFactory.getLogger(jdbc.JdbcUtil.class);

    static {
        init();
    }

    /**
     *获取数据库连接对象的方法，线程安全
     */
    public static Connection getConnection() throws SQLException {
        // 从当前线程中获取连接对象
        Connection connection = tl.get();
        // 判断为空的话，创建连接并绑定到当前线程
        if(connection == null) {
            synchronized(obj) {
                if(tl.get() == null) {
                    connection = createConnection();
                    tl.set(connection);
                }
            }
        }
        return connection;
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
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     *提交事务
     */
    public static void commit() {
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
    public static void rollback() {
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

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        jdbc.JdbcUtil.dataSource = dataSource;
        tl.remove();
    }

    /**
     *创建数据库连接
     */
    private static Connection createConnection() throws SQLException {
        if(dataSource == null) {
            throw new RuntimeException("创建数据源失败");
        }
        Connection conn = null;
        // 获得连接
        conn = dataSource.getConnection();
        return conn;
    }

    /**
     *根据指定配置文件创建数据源对象
     */
    private static void init() {
        try {
            HikariConfig config = new HikariConfig("/db.properties");
            dataSource = new HikariDataSource(config);
        } catch(Exception e) {
            log.error("创建数据源失败", e);
        }
    }
}