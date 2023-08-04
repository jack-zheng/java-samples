package samples.ioc;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrototypeTest {
    @Test
    public void test_prototype() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:db-testing.xml");
        IMockService service = context.getBean("mockService", IMockService.class);
        System.out.println(service.getName());
        System.out.println(service.getName());

        service = context.getBean("mockService", IMockService.class);
        System.out.println(service.getName());
    }
}
