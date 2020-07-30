package banking.db;

import java.sql.*;

public class DbConnection {

    private static final String JDBC_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@192.168.13.99:1521:db";
    private static final String USER = "intership_denis";
    private static final String PASSWORD = "db";


    static {
        try {
            Class.forName(JDBC_DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException cnfe) {
            throw new Error("Nu s-a putut incarca driver-ul JDBC.", cnfe);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {}
        }
    }
}
