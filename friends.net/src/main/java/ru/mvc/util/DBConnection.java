package ru.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection createConnection() {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/sem3";
        String username = "postgres";
        String password = "qwerty1";

        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return con;
    }
}
