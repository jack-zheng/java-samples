package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class SqlFuncTest {
    @Autowired
    private DataSource dataSource;
    @Test
    public void test() {
        SqlFunction<Integer> function = new SqlFunction(dataSource, "select count(*) from images");
        function.compile();

        int count = function.run();
        System.out.println(count);
    }
}
