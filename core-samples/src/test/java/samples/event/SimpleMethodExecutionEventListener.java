package samples.event;

public class SimpleMethodExecutionEventListener implements IMethodExecutionEventListener {
    @Override
    public void onMethodBegin(MethodExecutionEvent event) {
        System.out.println("Method begin: " + event.getMethodName());
    }

    @Override
    public void onMethodEnd(MethodExecutionEvent event) {
        System.out.println("Method end: " + event.getMethodName());
    }
}
