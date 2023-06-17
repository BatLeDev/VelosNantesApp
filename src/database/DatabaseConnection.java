package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://31.39.13.59:6543/velosNantes";
    private static final String DB_USER = "lecteur";
    private static final String DB_PASSWORD = "Est-ce que c'est bon pour vous ?";

    public static Statement getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }
}
