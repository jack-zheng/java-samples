package samples.spi;

import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.util.ServiceLoader;

public class SPITest {
    @Test
    public void test_search() {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        for (Search search : loader) {
            search.searchDoc("hello");
        }
    }

    @Test
    public void test_db_driver() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        for (Driver driver : loader) {
            System.out.println(driver.getClass().getName());
        }
    }

    @Test
    public void test_m() {
        System.out.println(System.getProperty("jdbc.drivers"));
    }
}
