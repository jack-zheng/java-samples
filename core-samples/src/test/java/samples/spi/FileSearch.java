package samples.spi;

import java.util.List;

public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("Now use FileSearch to search " + keyword);
        return null;
    }
}
