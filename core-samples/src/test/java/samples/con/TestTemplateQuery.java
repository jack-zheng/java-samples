package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Employee;
import samples.entity.Gender;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class TestTemplateQuery {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_queryForRowSet() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from employees where emp_no > 100 limit 3");
        while (rowSet.next()) {
            System.out.println(rowSet.getDate("birth_date"));
            System.out.println(rowSet.getString("first_name"));
        }
    }

    @Test
    public void test_execute_sql() throws SQLException {
        String ret = jdbcTemplate.query(
                "select * from employees where emp_no = 10001;",
                rs -> {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                    return null;
                });
        System.out.println(ret);
    }

    @Test
    public void test_queryForObj() {
        int ret = jdbcTemplate.queryForObject("select count(*) from employees", Integer.class);
        System.out.println(ret);

        Employee emp = jdbcTemplate.queryForObject("select * from employees where emp_no = ?", BeanPropertyRowMapper.newInstance(Employee.class), 10001);
        System.out.println(emp);
    }

    @Test
    public void test_queryForMap() {
        Map<String, Object> ret = jdbcTemplate.queryForMap("select * from employees limit 1");
        ret.forEach((k, v) -> System.out.println(k + " : " + v));

        Map<String, Object> ret2 = jdbcTemplate.queryForMap("select * from employees where emp_no = ?", 10001);
        ret2.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Test
    public void test_queryForList() {
        List<Map<String, Object>> ret = jdbcTemplate.queryForList("select * from employees limit 3");
        ret.forEach(System.out::println);
    }

    @Test
    public void test_query_ResultSetExtractor() {
        List<Employee> ret = jdbcTemplate.query(
                "select * from employees limit 3",
                new ResultSetExtractor<List<Employee>>() {
                    @Override
                    public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Employee> list = new ArrayList<>();
                        // loop rs, create Employee, add to list
                        while (rs.next()) {
                            Employee emp = new Employee();
                            emp.setEmpNo(rs.getInt(1));
                            emp.setBirthDate(rs.getDate(2));
                            emp.setFirstName(rs.getString(3));
                            emp.setLastName(rs.getString(4));
                            emp.setGender(Gender.valueOf(rs.getString(5)));
                            emp.setHireDate(rs.getDate(6));
                            list.add(emp);
                        }
                        return list;
                    }
                });
        ret.forEach(System.out::println);
    }

    @Test
    public void test_execute_sql_RowCallbackHandler() {
        List<Employee> ret = new ArrayList<>();
        jdbcTemplate.query(
                "select * from employees limit 3",
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        Employee emp = new Employee();
                        emp.setEmpNo(rs.getInt(1));
                        emp.setBirthDate(rs.getDate(2));
                        emp.setFirstName(rs.getString(3));
                        ret.add(emp);
                    }
                });
        ret.forEach(System.out::println);
    }

    @Test
    public void test_query_RowMapper() {
        List<Employee> ret = jdbcTemplate.query(
                "select * from employees limit 3",
                new RowMapper<Employee>() {
                    @Override
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Employee emp = new Employee();
                        emp.setEmpNo(rs.getInt(1));
                        emp.setBirthDate(rs.getDate(2));
                        return emp;
                    }
                });
        ret.forEach(System.out::println);
    }

    @Test
    public void test_query_BeanPropertiesRowMapper() {
        List<Employee> ret = new ArrayList<>();
        List<Employee> emps = jdbcTemplate.query(
                "select * from employees where emp_no < 10004",
                new BeanPropertyRowMapper<>(Employee.class)
        );
        emps.forEach(System.out::println);

        // test if no result found
        List<Employee> emps2 = jdbcTemplate.query(
                "select * from employees where emp_no < 10000",
                new BeanPropertyRowMapper<>(Employee.class)
        );
        System.out.println(emps2);
    }
}
