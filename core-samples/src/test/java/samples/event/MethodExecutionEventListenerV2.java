package samples.event;

import org.springframework.context.ApplicationListener;

public class MethodExecutionEventListenerV2 implements ApplicationListener<MethodExecutionEventV2> {

    @Override
    public void onApplicationEvent(MethodExecutionEventV2 event) {
        System.out.println("MethodExecutionEventListener: " + event.getMethodName());
    }
}
