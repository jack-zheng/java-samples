package samples.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class MethodExecutionEventPublisherV2 implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    public void methodToMonitor() {
        MethodExecutionEventV2 beginEvent = new MethodExecutionEventV2(this, "methodToMonitor", MethodExecutionStatus.BEGIN);
        this.applicationEventPublisher.publishEvent(beginEvent);
        System.out.println("execute: methodToMonitor");
        // execute methodToMonitor
        MethodExecutionEventV2 endEvent = new MethodExecutionEventV2(this, "methodToMonitor", MethodExecutionStatus.END);
        this.applicationEventPublisher.publishEvent(endEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
