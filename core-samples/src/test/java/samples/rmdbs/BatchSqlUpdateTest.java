package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.FxNewsBean;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class BatchSqlUpdateTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        BatchSqlUpdate batchSqlUpdate = new BatchSqlUpdate(dataSource,
                "insert into fx_news(news_id, news_title, news_body) values(?, ?, ?)");
        batchSqlUpdate.declareParameter(new SqlParameter(Types.INTEGER));
        batchSqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        batchSqlUpdate.declareParameter(new SqlParameter(Types.LONGNVARCHAR));
        batchSqlUpdate.compile();

        List<FxNewsBean> newsList = new ArrayList<>();
        newsList.add(new FxNewsBean(31, "Title 1", "Body 1"));
        newsList.add(new FxNewsBean(32, "Title 2", "Body 2"));
        newsList.forEach(news -> batchSqlUpdate.update(new Object[]{news.getNewsId(), news.getNewsTitle(), news.getNewsBody()}));

        batchSqlUpdate.flush();
    }
}
