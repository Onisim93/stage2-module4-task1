package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Properties properties = new Properties();
        try (InputStream is = H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties")) {
            properties.load(is);
            return DriverManager.getConnection(properties.getProperty("db_url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

