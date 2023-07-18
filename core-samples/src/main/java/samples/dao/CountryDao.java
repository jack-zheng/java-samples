package samples.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import samples.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static samples.dao.ConnectionManager.closeConnection;
import static samples.dao.ConnectionManager.openConnection;

public class CountryDao extends JdbcDaoSupport {
    private static final String GET_ALL_COUNTRIES_SQL =
            "select * from country";
    //private static final String GET_COUNTRIES_BY_NAME_SQL =
    //        "select * from country where name like ?";
    private static final String GET_COUNTRIES_BY_NAME_SQL =
            "select * from country where name like :name";

    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    //public List<Country> getCountryList() {
    //    List<Country> countryList = new ArrayList<>();
    //    try {
    //        Connection connection = openConnection();
    //        PreparedStatement statement =
    //                connection.prepareStatement(GET_ALL_COUNTRIES_SQL);
    //        ResultSet resultSet = statement.executeQuery();
    //        while (resultSet.next()) {
    //            countryList.add(new Country(resultSet.getString(2),
    //                    resultSet.getString(3)));
    //        }
    //        statement.close();
    //    } catch (SQLException e) {
    //        throw new RuntimeException(e);
    //    } finally {
    //        closeConnection();
    //    }
    //    return countryList;
    //}

    public List<Country> getCountryList() {
        List<Country> countryList =
                getJdbcTemplate().
                        query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
        return countryList;
    }

    //public List<Country> getCountryListStartWith(String name) {
    //    List<Country> countryList = new ArrayList<>();
    //    try {
    //        Connection connection = openConnection();
    //        PreparedStatement statement =
    //                connection.prepareStatement(GET_COUNTRIES_BY_NAME_SQL);
    //        statement.setString(1, name + "%");
    //        ResultSet resultSet = statement.executeQuery();
    //        while (resultSet.next()) {
    //            countryList.add(new Country(resultSet.getString(2),
    //                    resultSet.getString(3)));
    //        }
    //        statement.close();
    //    } catch (SQLException e) {
    //        throw new RuntimeException(e);
    //    } finally {
    //        closeConnection();
    //    }
    //    return countryList;
    //}

    public List<Country> getCountryListStartWith(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("name", name + "%");
        return namedParameterJdbcTemplate.
                query(GET_COUNTRIES_BY_NAME_SQL,
                        sqlParameterSource, COUNTRY_ROW_MAPPER);
    }
}
