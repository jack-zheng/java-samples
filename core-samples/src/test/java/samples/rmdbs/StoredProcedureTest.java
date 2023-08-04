package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.orm.CountTableStoreProcedure;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class StoredProcedureTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        CountTableStoreProcedure procedure = new CountTableStoreProcedure(dataSource);
        System.out.println(procedure.doCountTable("fx_news"));
    }
}
