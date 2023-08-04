package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.FxNewsBean;
import samples.orm.FxNewsQueryWithParameters;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class MappingSqlQueryWithParametersTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test_simple() {
        FxNewsQueryWithParameters query = new FxNewsQueryWithParameters(dataSource);
        List<FxNewsBean> news = query.execute("title");
        news.forEach(System.out::println);
    }

    @Test
    public void test_simple2() {
        Map<String, String> context = new HashMap<>();
        context.put("prefix", "FX");
        FxNewsQueryWithParameters query = new FxNewsQueryWithParameters(dataSource);
        List<FxNewsBean> news = query.execute("title", context);
        //news.forEach(sub -> System.out.println(sub.getNewsTitle()));
    }
}
