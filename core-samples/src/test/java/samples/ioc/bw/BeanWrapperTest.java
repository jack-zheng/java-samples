package samples.ioc.bw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import samples.ioc.InnerBean;
import samples.ioc.IocBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanWrapperTest {
    @Test
    public void test_set() {
        IocBean iocBean = new IocBean();
        BeanWrapper bw = new BeanWrapperImpl(iocBean);

        InnerBean innerBean = new InnerBean();
        innerBean.setName("Jack");
        bw.setPropertyValue("innerBean", innerBean);
        System.out.println(bw.getWrappedInstance());
    }

    @Test
    public void test_editor() {
        BeanWrapper bw = new BeanWrapperImpl(new InnerBean());
        //bw.registerCustomEditor(Date.class,
        //        new CustomDateEditor(new SimpleDateFormat("YYYY-MM-dd"), true));

        bw.setPropertyValue("date", "2021-01-01");
        System.out.println(bw.getWrappedInstance());
    }
}
