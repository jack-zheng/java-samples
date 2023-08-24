package samples.ioc.fb;

import org.springframework.stereotype.Service;

@Service
public class TestService01 implements IgnoredInterface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}
