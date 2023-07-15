package ru.sevastopall.http.util;

import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
            PROPERTIES.put("db.url", "jdbc:postgresql://localhost:5432/flight_repository");
            PROPERTIES.put("db.user", "postgres");
            PROPERTIES.put("db.password", "password");
            PROPERTIES.put("db.driver", "org.postgresql.Driver");
    }

    private PropertiesUtil() {}

    public static String get(String key) {
        return (String) PROPERTIES.get(key);
    }
}
