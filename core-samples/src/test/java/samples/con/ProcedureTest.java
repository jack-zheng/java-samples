package samples.con;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ProcedureTest {
    // Database connection details
    String url = "jdbc:mysql://localhost:3306/employees";
    String username = "root";
    String password = "123456";

    @BeforeEach
    public void setup() {

    }

    @Test
    public void test_create_procedure() {
        String procedureName = "GetRowCount";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Drop the stored procedure if it already exists (optional)
            String dropProcedureSql = "DROP PROCEDURE IF EXISTS " + procedureName + ";";
            Statement statement = conn.createStatement();
            statement.executeUpdate(dropProcedureSql);

            // Create the stored procedure
            String createProcedureSql = "CREATE PROCEDURE GetRowCount(IN tableName VARCHAR(255), OUT rowCount INT)\n" +
                    "BEGIN\n" +
                    "    SET @sql = CONCAT('SELECT COUNT(*) INTO @rowCount FROM employees.', tableName);\n" +
                    "    PREPARE stmt FROM @sql;\n" +
                    "    EXECUTE stmt;\n" +
                    "    DEALLOCATE PREPARE stmt;\n" +
                    "    SET rowCount = @rowCount;\n" +
                    "END;";
            statement.executeUpdate(createProcedureSql);

            System.out.println("Stored procedure created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_call_procedure() {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Prepare the SQL call for the stored procedure
            String callSql = "{call GetRowCount(?,?)}";
            CallableStatement callableStatement = conn.prepareCall(callSql);

            // Set the input parameters
            String tableName = "employees";
            callableStatement.setString(1, tableName);

            // Register the output parameter
            callableStatement.registerOutParameter(2, Types.INTEGER);

            // Execute the stored procedure
            callableStatement.execute();

            // Get the result from the output parameter
            int count = callableStatement.getInt(2);
            System.out.println("Row count: " + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
