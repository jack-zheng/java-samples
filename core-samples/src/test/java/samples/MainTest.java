package samples;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        System.out.println(context.getBean("e01"));
    }
}
