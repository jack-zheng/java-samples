package samples.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MockServiceImpl implements IMockService {
    @Autowired
    private ITmpDataBean tmpDataBean;

    @Override
    public String getName() {
        return tmpDataBean.toString();
    }
}
