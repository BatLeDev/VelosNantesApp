package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://31.39.13.59:6543/velosNantes";
    private static final String DB_USER = "lecteur";
    private static final String DB_PASSWORD = "Est-ce que c'est bon pour vous ?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
