package samples.ju5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(
        listeners = {MyTestExecutionListener.class},
        mergeMode = MERGE_WITH_DEFAULTS)
@ContextConfiguration("classpath:ju5-testing.xml")
public class TestSample {
    @Autowired
    private CounterService counterService;

    @Test
    public void test01() {
        System.out.println(this);
        System.out.println("test01 executed.");
        System.out.println(counterService.count());
    }

    @Test
    public void test02() {
        System.out.println("test02 executed.");
        System.out.println(this);
    }

}
