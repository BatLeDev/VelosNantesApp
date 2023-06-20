package database;

// SQL imports
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used create a connection to the database.
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://31.39.13.59:6543/velosNantes";
    private static final String DB_USER = "lecteur";
    private static final String DB_PASSWORD = "Est-ce que c'est bon pour vous ?";

    /**
     * Create a connection to the database.
     * @return {@link Statement}
     * @throws SQLException if the connection to the database failed.
     */
    public static Statement getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }
}
