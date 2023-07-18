package samples.con;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceTest {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "123456";

        // Create a SingleConnectionDataSource
        DataSource dataSource = new SingleConnectionDataSource(url, username, password, true);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Get a connection from the data source
            connection = dataSource.getConnection();

            // Create a statement object
            statement = connection.createStatement();

            // SQL query to get the count of rows in a table
            String sql = "SELECT COUNT(*) AS count FROM employees";

            // Execute the query and get the result set
            resultSet = statement.executeQuery(sql);

            // Process the result set
            if (resultSet.next()) {
                int rowCount = resultSet.getInt("count");

                System.out.println("Row count: " + rowCount);
            }
        } catch (SQLException e) {
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
