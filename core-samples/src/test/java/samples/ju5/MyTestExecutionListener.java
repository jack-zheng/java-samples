package samples.ju5;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class MyTestExecutionListener extends AbstractTestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        // log
        System.out.println("beforeTestClass executed.");
        System.out.println(testContext);
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        // log
        System.out.println("prepareTestInstance executed.");
        System.out.println(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        // log
        System.out.println("beforeTestMethod executed.");
        System.out.println(testContext);
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        // log
        System.out.println("beforeTestExecution executed.");
        System.out.println(testContext);
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        // log
        System.out.println("afterTestExecution executed.");
        System.out.println(testContext);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        // log
        System.out.println("afterTestMethod executed.");
        System.out.println(testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        // log
        System.out.println("afterTestClass executed.");
        System.out.println(testContext);
    }
}
