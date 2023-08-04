package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.orm.CapitalTitleUpdatableSqlQuery;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class UpdatableSqlQueryTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        String sql = "select * from fx_news";
        CapitalTitleUpdatableSqlQuery updatableSqlQuery = new CapitalTitleUpdatableSqlQuery(dataSource, sql);
        updatableSqlQuery.execute();
    }
}
