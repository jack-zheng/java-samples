package samples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FxNewsBean {
    private int newsId;

    private String newsTitle;

    private String newsBody;

    public FxNewsBean(int newsId) {
        this.newsId = newsId;
    }
}
