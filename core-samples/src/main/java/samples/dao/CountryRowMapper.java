package samples.dao;

import org.springframework.jdbc.core.RowMapper;
import samples.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    public static final String NAME = "name";
    public static final String CODE_NAME = "code_name";

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country(rs.getString(NAME), rs.getString(CODE_NAME));
        return country;
    }
}
