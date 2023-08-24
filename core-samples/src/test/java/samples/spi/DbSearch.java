package samples.spi;

import java.util.List;

public class DbSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("Now use DbSearch to search " + keyword);
        return null;
    }
}
