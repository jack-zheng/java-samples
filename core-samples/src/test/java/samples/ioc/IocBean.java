package samples.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.inject.Inject;

@Data
public class IocBean {
    private String id;
    @Autowired
    //@Inject
    private InnerBean innerBean;

    @Autowired
    private InnerBean02 innerBean02;
}
