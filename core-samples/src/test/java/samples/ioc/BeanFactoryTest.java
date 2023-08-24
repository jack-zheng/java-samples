package samples.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import samples.ju5.CounterService;
import samples.ju5.MyAnnotation;

import java.util.Arrays;

public class BeanFactoryTest {
    @Test
    public void test_getBeanProvider() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ju5-testing.xml");
        ObjectProvider<CounterService> provider = context.getBeanProvider(CounterService.class);
        System.out.println(provider);
        CounterService service = provider.getIfAvailable();
        System.out.println(service);
        System.out.println(service.count());
        service = provider.getIfAvailable();
        System.out.println(service);
        System.out.println(service.count());

    }

    @Test
    public void test_getBeansWithAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ju5-testing.xml");
        context.getBeansWithAnnotation(MyAnnotation.class).forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

        System.out.println(Arrays.toString(context.getBeanNamesForAnnotation(MyAnnotation.class)));
    }

    @Test
    public void test_findAnnotationOnBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ju5-testing.xml");
        System.out.println(context.findAnnotationOnBean("counterService", MyAnnotation.class));
    }

    @Test
    public void testAutowireCapableBeanFactory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc-testing.xml");
        System.out.println(context.getBean("innerBean"));

        IocBean bean = new IocBean();
        System.out.println(bean);

        context.getAutowireCapableBeanFactory().autowireBean(bean);
        System.out.println(bean);
    }

    @Test
    public void testAutowireCapableBeanFactory_create() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc-testing.xml");

        Object bean = context.getAutowireCapableBeanFactory().createBean(IocBean.class);
        System.out.println(bean);
    }

    @Test
    public void testAutowireCapableBeanFactory_02() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioc-testing.xml");
        System.out.println(context.getBean("innerBean"));
        IocBean iocBean = new IocBean();
        System.out.println(iocBean);
        context.getAutowireCapableBeanFactory().autowireBeanProperties(iocBean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
        System.out.println(iocBean);
    }

    @Test
    public void test_ConfigurableBeanFactory() {
        Resource res = new DefaultResourceLoader().getResource("classpath:ioc-testing.xml");
        ConfigurableListableBeanFactory factory = new XmlBeanFactory(res);
        System.out.println(factory.getBeanPostProcessorCount());
        System.out.println(Arrays.toString(factory.getRegisteredScopeNames()));
        System.out.println(factory.getSingletonCount());
        System.out.println(factory.getBean("innerBean"));
        System.out.println(factory.getSingletonCount());
    }

    @Test
    public void test_ConfigurableBeanFactory_02() {
        Resource res = new DefaultResourceLoader().getResource("classpath:ioc-testing.xml");
        ConfigurableListableBeanFactory factory = new XmlBeanFactory(res);
        //factory.getMergedBeanDefinition()
    }
}
