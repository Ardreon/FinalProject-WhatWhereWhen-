package com.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    private final String url;
    private final String user;
    private final String password;

    public ConnectorDB(String filename) {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        this.url = resource.getString("jdbc:mysql://localhost:3306/mysql?useSSL=false");
        this.user = resource.getString("root");
        this.password = resource.getString("admin");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}