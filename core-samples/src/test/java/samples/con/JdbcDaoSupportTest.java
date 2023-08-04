package samples.con;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class JdbcDaoSupportTest {
    @Autowired
    private TestImgDao testImgDao;

    @Test
    public void test() {
        System.out.println(testImgDao.countRow());
    }
}
