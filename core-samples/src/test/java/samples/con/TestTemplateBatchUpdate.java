package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Employee;
import samples.entity.Gender;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class TestTemplateBatchUpdate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_update() {
        // create a list employee, with unique name
        List<Employee> list = List.of(
                new Employee(1, new Date(), "A1", "B1", Gender.M, new Date()),
                new Employee(2, new Date(), "A2", "B2", Gender.M, new Date()),
                new Employee(3, new Date(), "A3", "B3", Gender.M, new Date()),
                new Employee(4, new Date(), "A4", "B4", Gender.M, new Date()));


        int[] ret = jdbcTemplate.batchUpdate(
                "insert into employees values (?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Employee e = list.get(i);
                        ps.setInt(1, e.getEmpNo());
                        ps.setDate(2, new java.sql.Date(e.getBirthDate().getTime()));
                        ps.setString(3, e.getFirstName());
                        ps.setString(4, e.getLastName());
                        ps.setString(5, e.getGender().name());
                        ps.setDate(6, new java.sql.Date(e.getHireDate().getTime()));
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
        System.out.println(Arrays.toString(ret));
    }
}
