package samples.orm;

import org.springframework.jdbc.object.UpdatableSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CapitalTitleUpdatableSqlQuery extends UpdatableSqlQuery {

    public CapitalTitleUpdatableSqlQuery(DataSource ds, String sql) {
        super(ds, sql);
        compile();
    }

    @Override
    protected Object updateRow(ResultSet rs, int rowNum, Map context) throws SQLException {
        String title = rs.getString("news_title");
        rs.updateString("news_title", title.toUpperCase());
        return null;
    }
}
