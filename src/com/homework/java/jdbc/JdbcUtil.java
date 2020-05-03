package com.homework.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/javaee?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "hualing.5251";
    public static Connection conn = null;
    public static PreparedStatement stmt = null;

    public void Connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        try {
            if(stmt!=null) stmt.close();
        } catch(SQLException se2){ }

        try {
            if(conn!=null) conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
}
