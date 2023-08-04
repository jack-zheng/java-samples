package samples.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

public class ResourceTest {
    @Test
    public void test() {
        StaticMessageSource source = new StaticMessageSource();
        source.addMessage("menu.file", Locale.US, "File({0})");
        source.addMessage("menu.edit", Locale.US, "Edit");

        System.out.println(source.getMessage("menu.file", new Object[]{"F"}, Locale.US));
        System.out.println(source.getMessage("menu.edit", null,"Edit", Locale.US));
    }
}
