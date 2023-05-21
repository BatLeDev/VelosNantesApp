package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://192.168.1.150:3306/velosNantes";
    private static final String DB_USER = "devs";
    private static final String DB_PASSWORD = "oulalaTuN.aPaslemotdepasse";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
