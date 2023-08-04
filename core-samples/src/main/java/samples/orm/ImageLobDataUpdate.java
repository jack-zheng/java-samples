package samples.orm;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;

public class ImageLobDataUpdate extends SqlUpdate {
    private static final String SQL = "update images set entity = ? where filename = ?";
    private LobHandler lobHandler = new DefaultLobHandler();

    public ImageLobDataUpdate(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter(Types.BLOB));
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    public int replaceImageData(String filename, File imageFile) throws IOException {
        InputStream ins = null;
        try {
            ins = new FileInputStream(imageFile);
            SqlLobValue lobValue = new SqlLobValue(ins, (int) imageFile.length(), getLobHandler());
            return update(lobValue, filename);
        } finally {
            IOUtils.closeQuietly(ins);
        }
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }
}

