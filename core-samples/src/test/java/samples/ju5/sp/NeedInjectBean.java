package samples.ju5.sp;

import org.springframework.beans.factory.annotation.Autowired;

public class NeedInjectBean {
    @Autowired
    private InjectedBean injectedBean;

    public InjectedBean getInjectedBean() {
        return injectedBean;
    }

    public void setInjectedBean(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Override
    public String toString() {
        return "NeedInjectBean{" +
                "injectedBean=" + injectedBean +
                '}';
    }
}
