package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class TestWithDataSourceInjected {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test_datasource_init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employees", Integer.class);
        System.out.println("Row count: " + rowCount);
    }

    @Test
    public void test_execute_sql() throws SQLException {
        // execute(sql) is a general
        Connection con = dataSource.getConnection();
        boolean ret = con.createStatement().execute("select * from employees where emp_no = 10001;");
        System.out.println("select ret: " + ret);
        ret = con.createStatement().execute("update employees set gender = 'F' where emp_no = 10001;");
        System.out.println("update ret: " + ret);
    }
}
