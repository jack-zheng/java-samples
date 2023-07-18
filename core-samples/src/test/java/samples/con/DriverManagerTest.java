package samples.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverManagerTest {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "123456";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection to the database
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT count(*) as CNT FROM employees");
            if (resultSet.next()) {
                int rowCount = resultSet.getInt("CNT");

                System.out.println("Row count: " + rowCount);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error executing the query");
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the resources");
                e.printStackTrace();
            }
        }
    }
}
