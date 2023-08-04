package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class NamedParameterJdbcTemplateTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test_simple() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("filename", "img.png");
        int count = jdbcTemplate.queryForObject("select count(*) from images where filename = :filename", sqlParameterSource, Integer.class);
        System.out.println(count);
    }
}
