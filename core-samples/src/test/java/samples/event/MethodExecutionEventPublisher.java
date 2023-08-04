package samples.event;

import java.util.ArrayList;
import java.util.List;

public class MethodExecutionEventPublisher {
    private List<IMethodExecutionEventListener> listeners = new ArrayList<>();

    public void methodToMonitor() {
        MethodExecutionEvent event2Publish = new MethodExecutionEvent(this, "methodToMonitor");
        publishEvent(MethodExecutionStatus.BEGIN, event2Publish);
        System.out.println("methodToMonitor");
        publishEvent(MethodExecutionStatus.END, event2Publish);
    }

    protected void publishEvent(MethodExecutionStatus status, MethodExecutionEvent event) {
        List<IMethodExecutionEventListener> copyOfListeners = new ArrayList<>(listeners);
        for (IMethodExecutionEventListener listener : copyOfListeners) {
            if (MethodExecutionStatus.BEGIN.equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        }
    }

    public void addMethodExecutionEventListener(IMethodExecutionEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeMethodExecutionEventListener(IMethodExecutionEventListener listener) {
        this.listeners.remove(listener);
    }

    public void removeAllListeners() {
        this.listeners.clear();
    }

    public static void main(String[] args) {
        MethodExecutionEventPublisher publisher = new MethodExecutionEventPublisher();
        publisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());
        publisher.methodToMonitor();
    }
}
