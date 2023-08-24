package samples.ju5;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class MockSpringExtension implements BeforeAllCallback, AfterAllCallback, TestInstancePostProcessor,
        BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback,
        ParameterResolver {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("MockSpringExtension.beforeAll");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("MockSpringExtension.afterAll");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        // print log
        System.out.println("MockSpringExtension.afterEach");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        // print log
        System.out.println("MockSpringExtension.afterTestExecution");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        // print log
        System.out.println("MockSpringExtension.beforeEach");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        // print log
        System.out.println("MockSpringExtension.beforeTestExecution");
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // print log
        System.out.println("MockSpringExtension.supportsParameter");
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // print log
        System.out.println("MockSpringExtension.resolveParameter");
        return null;
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        // print log
        System.out.println("MockSpringExtension.postProcessTestInstance");
    }
}
