package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class TestTemplateUpdate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_update() {
        int ret = jdbcTemplate.update("update employees set gender = ? where emp_no = ?", "M", 10001);
        System.out.println(ret);
    }

    @Test
    public void test_update_with_pps() {
        int ret = jdbcTemplate.update(
                "update employees set gender = ? where emp_no = ?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, "M");
                        ps.setInt(2, 10001);
                    }
                }
        );
        System.out.println(ret);
    }

    @Test
    public void test_update_with_pp_creator() {
        int ret = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("update employees set gender = ? where emp_no = ?");
                ps.setString(1, "M");
                ps.setInt(2, 10001);
                return ps;
            }
        });

        System.out.println(ret);
    }
}
