package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class SpringProcedureTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_create_procedure() {
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS GetRowCount;");
        jdbcTemplate.execute("CREATE PROCEDURE GetRowCount(IN tableName VARCHAR(255), OUT rowCount INT)\n" +
                "BEGIN\n" +
                "    SET @sql = CONCAT('SELECT COUNT(*) INTO @rowCount FROM employees.', tableName);\n" +
                "    PREPARE stmt FROM @sql;\n" +
                "    EXECUTE stmt;\n" +
                "    DEALLOCATE PREPARE stmt;\n" +
                "    SET rowCount = @rowCount;\n" +
                "END;");

        String procedureStr = "call GetRowCount(?,?)";
        Map<String, Object> ret = jdbcTemplate.execute(procedureStr, new CallableStatementCallback<Map<String, Object>>() {
            @Override
            public Map<String, Object> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setString(1, "employees");
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();

                Map<String, Object> result = new HashMap<>();
                result.put("count", cs.getInt(2));
                return result;
            }
        });

        System.out.println("---> row count: " + ret.get("count"));
    }

    @Test
    public void test_create_procedure_v2() {
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS GetRowCount;");
        jdbcTemplate.execute("CREATE PROCEDURE GetRowCount(IN tableName VARCHAR(255), OUT rowCount INT)\n" +
                "BEGIN\n" +
                "    SET @sql = CONCAT('SELECT COUNT(*) INTO @rowCount FROM employees.', tableName);\n" +
                "    PREPARE stmt FROM @sql;\n" +
                "    EXECUTE stmt;\n" +
                "    DEALLOCATE PREPARE stmt;\n" +
                "    SET rowCount = @rowCount;\n" +
                "END;");

        String procedureStr = "call GetRowCount(?,?)";
        Map<String, Object> ret = jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall(procedureStr);
                cs.setString(1, "employees");
                cs.registerOutParameter(2, Types.INTEGER);
                return cs;
            }
        }, new CallableStatementCallback<Map<String, Object>>() {
            @Override
            public Map<String, Object> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.execute();
                Map<String, Object> result = new HashMap<>();
                result.put("count", cs.getInt(2));
                return result;
            }
        });

        System.out.println("---> row count: " + ret.get("count"));
    }

    @Test
    public void test_create_procedure_v3() {
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS GetRowCount;");
        jdbcTemplate.execute("CREATE PROCEDURE GetRowCount(IN tableName VARCHAR(255), OUT rowCount INT)\n" +
                "BEGIN\n" +
                "    SET @sql = CONCAT('SELECT COUNT(*) INTO @rowCount FROM employees.', tableName);\n" +
                "    PREPARE stmt FROM @sql;\n" +
                "    EXECUTE stmt;\n" +
                "    DEALLOCATE PREPARE stmt;\n" +
                "    SET rowCount = @rowCount;\n" +
                "END;");

        String procedureStr = "call GetRowCount(?,?)";
        List<SqlParameter> parameterList = new ArrayList<>();
        parameterList.add(new SqlParameter(Types.VARCHAR));
        parameterList.add(new SqlOutParameter("count", Types.INTEGER));

        Map<String, Object> ret = jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall(procedureStr);
                cs.setString(1, "employees");
                cs.registerOutParameter(2, Types.INTEGER);
                return cs;
            }
        }, parameterList);


        System.out.println("---> row count: " + ret.get("count"));
    }
}
