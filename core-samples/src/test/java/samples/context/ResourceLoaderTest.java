package samples.context;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class ResourceLoaderTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:hana-db.testing.xml");
        System.out.println(resource.getFilename());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] res = resolver.getResources("*.xml");
        for (Resource re : res) {
            System.out.println(re.getFilename());
        }
    }

    @Test
    public void test_print_all_bean_name() {
        ApplicationContext context = new ClassPathXmlApplicationContext("db-testing.xml");
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        // 这一句会抛错，是不是 autowire 的 loader 是 junit 的 spring extension 创建的，所以我并不能直接使用？
        ResourceLoader resourceLoader = (ResourceLoader) context.getBean("resourceLoader");
        System.out.println(resourceLoader.hashCode());
    }
}
