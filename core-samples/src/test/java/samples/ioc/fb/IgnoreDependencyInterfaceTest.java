package samples.ioc.fb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class IgnoreDependencyInterfaceTest {

    /**
     * 我猜测，这个方法是用来忽略依赖接口的，比如 xml 中有 intf-a, class B 有 autowire 这个 intf-a,
     * 加了这个注解后，beanFactory 就不会作注入了
     */
    @Test
    public void test_ignoreDependencyInterfaceTest() {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("ioc-bf-testing.xml"));

        // Ignore the specified interface when searching for autowire candidates
        //factory.ignoreDependencyInterface(IgnoredInterface.class);

        // Create and initialize beansgg
        CallerService callerService = factory.getBean(CallerService.class);
        callerService.printInjectStatus();

        factory.getBean(TestService01.class).doSomething();
    }
}
