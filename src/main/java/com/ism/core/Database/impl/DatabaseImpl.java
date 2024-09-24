package com.ism.core.Database.impl;

import java.sql.*;

import com.ism.core.Database.Database;

public class DatabaseImpl implements Database {

    private final String url = "jdbc:mysql://localhost:8889/gestion_dette_glrs_2024";
    private final String user = "root";
    private final String password = "root";
    protected PreparedStatement ps;
    protected Connection conn = null;

    @Override
    public void getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Override
    public ResultSet executeQuery() throws SQLException {

        return ps.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {

        return ps.executeUpdate();
    }

    @Override
    public void initPreparedStatement(String sql) throws SQLException {

        if (sql.toUpperCase().trim().startsWith("INSERT")) {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = conn.prepareStatement(sql);
        }

    }


}
