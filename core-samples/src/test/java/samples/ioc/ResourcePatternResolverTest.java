package samples.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class ResourcePatternResolverTest {
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    @Test
    public void test() {
        Resource resource = resolver.getResource("classpath:ju5-testing.xml");
        System.out.println(resource.getFilename());
    }

    @Test
    public void test02() throws IOException {
        Resource[] resources = resolver.getResources("*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
        }
    }
}
