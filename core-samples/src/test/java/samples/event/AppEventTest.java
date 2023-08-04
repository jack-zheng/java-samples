package samples.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class AppEventTest {
    @Autowired
    private MethodExecutionEventPublisherV2 methodExecutionEventPublisherV2;

    @Test
    public void test_publish_event() {
        methodExecutionEventPublisherV2.methodToMonitor();
    }
}
