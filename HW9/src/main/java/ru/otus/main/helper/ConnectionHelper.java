package ru.otus.main.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    /**
     * @return
     */
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://" +
                    "localhost:" +
                    "3306/" +
                    "test?" +
                    "useSSL=false&" +
                    "user=root&" +
                    "password=1234";

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
