package samples.orm;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import samples.entity.Image;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LobHandlingSqlQuery extends MappingSqlQuery<Image> {
    private static final String SQL = "select * from images where id = ?";

    private LobHandler lobHandler = new DefaultLobHandler();

    public LobHandlingSqlQuery(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }

    @Override
    protected Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt(1));
        image.setFilename(rs.getString(2));
        image.setEntity(lobHandler.getBlobAsBytes(rs, 3));
        image.setDescription(rs.getString(4));
        return image;
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }
}
