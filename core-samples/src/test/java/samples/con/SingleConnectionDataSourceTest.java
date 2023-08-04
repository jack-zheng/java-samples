package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class SingleConnectionDataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test_reconnect() throws SQLException {
        Connection con = dataSource.getConnection();
        SingleConnectionDataSource scds = new SingleConnectionDataSource(con, true);
        System.out.println(scds);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(scds);
        jdbcTemplate.query("select * from employees limit 3", rs -> {
            System.out.println(rs.getString("first_name"));
        });
        scds.destroy();

        scds.resetConnection();
        System.out.println(scds);
        jdbcTemplate.query("select * from employees", rs -> {
            System.out.println(rs.getString("first_name"));
        });
    }
}
