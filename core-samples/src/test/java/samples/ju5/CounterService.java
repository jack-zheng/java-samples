package samples.ju5;

@Deprecated
@MyAnnotation
public class CounterService implements ICounter {
    private static int count = 0;

    @Override
    public int count() {
        return count++;
    }
}
