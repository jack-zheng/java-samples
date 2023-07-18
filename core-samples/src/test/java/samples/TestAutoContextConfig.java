package samples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Employee;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestAutoContextConfig {

    @Autowired
    private Employee e01;

    @Test
    public void testContextConfig() {
        System.out.println(e01);
    }
}
