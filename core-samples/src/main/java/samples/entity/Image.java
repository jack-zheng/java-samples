package samples.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Image {
    private int id;
    private String filename;
    private byte[] entity;
    private String description;
}
