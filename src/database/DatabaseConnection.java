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

    // TODO - Use environment variables to store the database credentials.
    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

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
