package samples.ioc;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefTest {
    @Test
    public void test_bean_def_prop() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc-testing.xml");

        BeanDefinition bd = context.getBeanFactory().getBeanDefinition("innerBean");
        System.out.println(bd);

    }
}
