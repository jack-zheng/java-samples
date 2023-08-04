package samples.rmdbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.orm.ImageLobDataUpdate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class ImageLobDataUpdateTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException {
        ImageLobDataUpdate update = new ImageLobDataUpdate(dataSource);
        update.replaceImageData("img2.png", resourceLoader.getResource("classpath:img_2.png").getFile());
    }
}
