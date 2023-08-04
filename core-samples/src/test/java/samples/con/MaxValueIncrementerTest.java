package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

/**
 * 先运行下面的SQL脚本，准别测试环境
 *
 * create table fx_news (
 * 	news_id bigint(20) not null,
 * 	news_title varchar(25) not null,
 * 	news_body text not null,
 * 	primary key(news_id)
 * )
 *
 * create table fx_news_key (
 * 	value bigint(20) not null default 0,
 * 	primary key(value)
 * ) engine=MYISAM
 *
 * insert into fx_news_key values(0);
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class MaxValueIncrementerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DataFieldMaxValueIncrementer incrementer;

    @Test
    public void test_incrementer() {
        DataFieldMaxValueIncrementer incrementer = new MySQLMaxValueIncrementer(
                dataSource, "fx_news_key", "value"
        );

        ((MySQLMaxValueIncrementer)incrementer).setCacheSize(10);
        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into fx_news(news_id, news_title, news_body) values(?, ?, ?)",
                    incrementer.nextLongValue(), "title", "body");
        }
    }

    @Test
    public void test_inject_incrementer() {
        jdbcTemplate.update("insert into fx_news(news_id, news_title, news_body) values(?, ?, ?)",
                incrementer.nextLongValue(), "title", "body");
    }
}
