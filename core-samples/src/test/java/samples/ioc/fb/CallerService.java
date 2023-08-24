package samples.ioc.fb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallerService {
    @Autowired
    private IgnoredInterface ignoredInterface;
    @Autowired
    private InjectedInterface injectedInterface;

    public void printInjectStatus() {
        System.out.println("ignoredInterface is " + ignoredInterface);
        System.out.println("injectedInterface is " + injectedInterface);
    }
}
