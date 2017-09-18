package com.gestion.DataSource.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataSource {

    private static DataSource ds = null;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/brasserie?useUnicode=true";
    private static final String USER = "root";
    private static final String PASS = "";

    private DataSource() {

    }

    public static DataSource getInstace() {
        if (ds == null) {
            ds = new DataSource();
        }
        return ds;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e.getMessage());
            e.fillInStackTrace();
        }
        return conn;
    }
}
