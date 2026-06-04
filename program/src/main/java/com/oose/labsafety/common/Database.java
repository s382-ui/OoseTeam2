package com.oose.labsafety.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {

    private static final String CONFIG_FILE = "db.properties";
    private static final Properties PROPERTIES = loadProperties();

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                property("db.url", "jdbc:mysql://127.0.0.1:3306/lab_safety?serverTimezone=Asia/Seoul&characterEncoding=utf8&useUnicode=true"),
                property("db.user", "root"),
                property("db.password", "")
        );
    }

    private static String property(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return PROPERTIES.getProperty(key, defaultValue);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("DB 설정 파일을 읽을 수 없습니다: " + CONFIG_FILE, exception);
        }
        return properties;
    }
}
