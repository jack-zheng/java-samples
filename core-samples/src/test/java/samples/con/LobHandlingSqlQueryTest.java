package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Image;
import samples.orm.LobHandlingSqlQuery;

import javax.sql.DataSource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class LobHandlingSqlQueryTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        LobHandlingSqlQuery query = new LobHandlingSqlQuery(dataSource);
        List<Image> list = query.execute(3);
        System.out.println(list.get(0).getEntity().length);
    }
}
