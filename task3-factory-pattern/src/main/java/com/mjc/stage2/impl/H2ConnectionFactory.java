package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Properties properties = new Properties();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        try (FileInputStream is = new FileInputStream(rootPath + "h2database.properties")) {
            properties.load(is);
            return DriverManager.getConnection(properties.getProperty("db_url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

