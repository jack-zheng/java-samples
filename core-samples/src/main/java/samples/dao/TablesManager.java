package samples.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static samples.dao.ConnectionManager.*;

public class TablesManager {
    public static void createTable() {
        String sql = "CREATE TABLE COUNTRY( ID IDENTITY, NAME VARCHAR (255), CODE_NAME VARCHAR(255) );";
        executeStatement(sql);
    }

    public static void dropTable() {
        String sql = "DROP TABLE IF EXISTS COUNTRY;";
        executeStatement(sql);
    }

    private static void executeStatement(String sql) {
        PreparedStatement statement;

        try {
            Connection connection = openConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}
