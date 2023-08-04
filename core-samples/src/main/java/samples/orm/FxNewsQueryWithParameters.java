package samples.orm;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import samples.entity.FxNewsBean;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class FxNewsQueryWithParameters extends MappingSqlQueryWithParameters<FxNewsBean> {
    private static final String QUERY_SQL = "select * from fx_news where news_title = ?";

    public FxNewsQueryWithParameters(DataSource dataSource) {
        super(dataSource, QUERY_SQL);
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    @Override
    protected FxNewsBean mapRow(ResultSet rs, int rowNum, Object[] parameters, Map context) throws SQLException {
        FxNewsBean fxNewsBean = new FxNewsBean();
        fxNewsBean.setNewsId(rs.getInt(1));
        fxNewsBean.setNewsTitle(rs.getString(2));
        fxNewsBean.setNewsBody(rs.getString(3));
        return fxNewsBean;
    }
}
