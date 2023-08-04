package samples.event;

import java.util.EventListener;

public interface IMethodExecutionEventListener extends EventListener {
    void onMethodBegin(MethodExecutionEvent event);

    void onMethodEnd(MethodExecutionEvent event);
}
