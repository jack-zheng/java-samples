package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.orm.NewsTitleSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class SqlUpdateTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        String sql = "update fx_news set news_title = ? where news_id = ?";
        SqlUpdate sqlUpdate = new SqlUpdate(dataSource, sql);
        sqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter(Types.INTEGER));
        sqlUpdate.compile();

        int rows = sqlUpdate.update("New Title", 1);
        System.out.println(rows);
    }

    @Test
    public void test2() {
        NewsTitleSqlUpdate update = new NewsTitleSqlUpdate(dataSource);
        int rows = update.updateTitleById("New Title", 1);
        System.out.println(rows);
    }
}
