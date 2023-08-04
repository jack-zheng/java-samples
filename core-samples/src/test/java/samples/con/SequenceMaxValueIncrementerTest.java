package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.HanaSequenceMaxValueIncrementer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:hana-db-testing.xml")
public class SequenceMaxValueIncrementerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DataFieldMaxValueIncrementer incrementer;

    @Test
    public void test_incrementer_init() {
        int ret = jdbcTemplate.queryForObject(
                "select count(*) from bizx_bizxtest.user_account;", Integer.class
        );
        System.out.println(ret);
    }

    /**
     * 先运行下面的SQL脚本，准别测试环境
     * CREATE SEQUENCE change_on_setup.fx_news_seq START WITH 1 INCREMENT BY 1 NO MAXVALUE CACHE 20;
     *
     * CREATE COLUMN TABLE BIZX_BIZXTEST.fx_news (
     *   news_id BIGINT NOT NULL,
     *   news_title NVARCHAR(25) NOT NULL,
     *   news_body NVARCHAR(1000) NOT NULL,
     *   PRIMARY KEY (news_id)
     * );
     *
     * test:
     * SELECT  BIZX_BIZXTEST.fx_news_seq.nextval AS current_value FROM DUMMY;
     */
    @Test
    public void test_sequence_incrementer() {
        DataFieldMaxValueIncrementer incrementer = new HanaSequenceMaxValueIncrementer(dataSource, "BIZX_BIZXTEST.fx_news_seq");

        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into BIZX_BIZXTEST.fx_news(news_id, news_title, news_body) values(?, ?, ?)",
                    incrementer.nextLongValue(), "title", "body");
        }
    }

    @Test
    public void test_sequence_incrementer_v2() {
        for (int i = 0; i < 10; i++) {
            jdbcTemplate.update("insert into BIZX_BIZXTEST.fx_news(news_id, news_title, news_body) values(?, ?, ?)",
                    incrementer.nextLongValue(), "title", "body");
        }
    }
}
