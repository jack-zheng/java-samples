package samples.orm;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CountTableStoreProcedure extends StoredProcedure {
    private static final String PROCEDURE_NAME = "GetRowCount";
    private static final String IN_PARAM_NAME = "table_name";
    private static final String OUT_PARAM_NAME = "table_count";

    public CountTableStoreProcedure(DataSource dataSource) {
        super(dataSource, PROCEDURE_NAME);
        declareParameter(new SqlParameter(IN_PARAM_NAME, Types.VARCHAR));
        declareParameter(new SqlOutParameter(OUT_PARAM_NAME, Types.INTEGER));
        compile();
    }

    public int doCountTable(String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put(IN_PARAM_NAME, tableName);

        Map<String, Object> results = execute(params);
        return (int) results.get(OUT_PARAM_NAME);
    }
}
