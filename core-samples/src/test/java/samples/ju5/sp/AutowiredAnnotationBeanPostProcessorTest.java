package samples.ju5.sp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredAnnotationBeanPostProcessorTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NeedInjectBean.class);
        context.registerBean(InjectedBean.class, () -> new InjectedBean("key"));
        context.refresh();

        BeanDefinition bd = context.getBeanDefinition("needInjectBean");
        System.out.println(bd);

        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        NeedInjectBean bean = factory.getBean("needInjectBean", NeedInjectBean.class);
        System.out.println(bean);

        NeedInjectBean bean2 = new NeedInjectBean();
        System.out.println("before: " + bean2);
        factory.autowireBeanProperties(bean2, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        System.out.println("after:" + bean2);
    }
}
