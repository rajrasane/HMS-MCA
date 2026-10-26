package com.hms.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConfig {
    private final Properties properties = new Properties();

    public DatabaseConfig() {
        try (InputStream input = DatabaseConfig.class.getResourceAsStream("/config/application.properties")) {
            if (input != null) properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read database configuration", e);
        }
    }

    public Connection openConnection() throws SQLException {
        String url = value("DB_URL", "db.url", "jdbc:mysql://localhost:3306/hospital?serverTimezone=UTC");
        String user = value("DB_USER", "db.user", "root");
        String password = value("DB_PASSWORD", "db.password", "");
        return DriverManager.getConnection(url, user, password);
    }

    private String value(String environment, String property, String fallback) {
        String env = System.getenv(environment);
        return env != null && !env.isBlank() ? env : properties.getProperty(property, fallback);
    }
}
