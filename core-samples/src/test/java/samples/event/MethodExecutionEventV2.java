package samples.event;

import org.springframework.context.ApplicationEvent;

public class MethodExecutionEventV2 extends ApplicationEvent {
    private static final long serialVersionUID = -6737677766931616937L;
    private String methodName;
    private MethodExecutionStatus status;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public MethodExecutionEventV2(Object source) {
        super(source);
    }

    public MethodExecutionEventV2(Object source, String methodName, MethodExecutionStatus status) {
        super(source);
        this.methodName = methodName;
        this.status = status;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public MethodExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(MethodExecutionStatus status) {
        this.status = status;
    }
}
