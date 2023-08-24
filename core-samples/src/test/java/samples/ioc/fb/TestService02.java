package samples.ioc.fb;

import org.springframework.stereotype.Service;

@Service
public class TestService02 implements InjectedInterface {
    @Override
    public void doOtherThing() {
        System.out.println("doOtherThing");
    }
}
