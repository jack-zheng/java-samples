package samples.orm;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class NewsTitleSqlUpdate extends SqlUpdate {
    private static final String sql = "update fx_news set news_title = ? where news_id = ?";

    public NewsTitleSqlUpdate(DataSource ds) {
        super(ds, sql);
        declareParameter(new SqlParameter(Types.VARCHAR));
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }

    public int updateTitleById(String title, int id) {
        return update(title, id);
    }
}
