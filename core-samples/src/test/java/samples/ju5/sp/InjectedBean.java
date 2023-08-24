package samples.ju5.sp;

public class InjectedBean {
    private String key;

    public InjectedBean(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "InjectedBean{" +
                "key='" + key + '\'' +
                '}';
    }
}
