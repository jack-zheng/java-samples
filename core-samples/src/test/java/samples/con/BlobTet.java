package samples.con;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CREATE TABLE images (
 *  id int(11) NOT NULL,
 *  filename varchar(255) NOT NULL,
 *  entity blob NOT NULL,
 *  description text NULL,
 *  PRIMARY KEY (id)
 * );
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:db-testing.xml")
public class BlobTet {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_save_blob() throws Exception {
        //Resource resource = resourceLoader.getResource("img_1.png");
        //System.out.println(resource.contentLength());
        //InputStream is = resource.getInputStream();
        //
        //Connection con = dataSource.getConnection();
        //PreparedStatement ps = con.prepareStatement("insert into images(id, filename, entity) values(?, ?, ?)");
        //ps.setInt(1, 3);
        //ps.setString(2, "img.png");
        //ps.setBinaryStream(3, is);
        //ps.executeUpdate();
        //ps.close();
        //con.close();
        //
        //IOUtils.closeQuietly(is);
        //
        File imageFile = new File("out.png");
        InputStream ins = null;

        Connection con2 = dataSource.getConnection();
        Statement stmt = con2.createStatement();
        ResultSet rs = stmt.executeQuery("select entity from images where id = 3");
        while (rs.next()) {
            ins = rs.getBinaryStream(1);
        }
        rs.close();
        stmt.close();
        con2.close();

        OutputStream os = new FileOutputStream(imageFile);
        IOUtils.write(IOUtils.toByteArray(ins), os);
        IOUtils.closeQuietly(ins);
        IOUtils.closeQuietly(os);
    }

    @Test
    public void test_image_table() throws Exception {
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into images(id, filename, entity, description) values(?, ?, ?, ?)");
        ps.setInt(1, 2);
        ps.setString(2, "img.png");
        ps.setBinaryStream(3, new ByteArrayInputStream(new byte[0]));
        ps.setString(4, "nothing to say");
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    @Test
    public void test_insert_blob_using_spring() throws IOException {
        Resource resource = resourceLoader.getResource("img_1.png");
        System.out.println(resource.getInputStream().readAllBytes().length);
        InputStream is = resource.getInputStream();
        //
        LobHandler lobHandler = new DefaultLobHandler();
        // 之前用的 update，一直抛错，原来需要调用 execute 方法，update 的时候参数不对。
        //jdbcTemplate.update("insert into images(id, filename, entity, description) values(?, ?, ?, ?)",
        //        new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
        //            @SneakyThrows
        //            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
        //                ps.setInt(1, 8);
        //                ps.setString(2, "img_spring.png");
        //                lobCreator.setBlobAsBinaryStream(ps, 3, is, -1);
        //                ps.setString(4, "nothing to say");
        //            }
        //        });

        jdbcTemplate.execute(
                "insert into images(id, filename, entity, description) values(?, ?, ?, ?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
                        ps.setLong(1, 7);
                        ps.setString(2, "img_spring.png");
                        lobCreator.setBlobAsBinaryStream(ps, 3, is, -1);
                        ps.setString(4, "nothing to say");
                    }
                }
        );
        System.out.println("Finish insert");
    }

    @Test
    public void test_read_blob() throws IOException {
        LobHandler lobHandler = new DefaultLobHandler();
        Map<String, Object> m = jdbcTemplate.queryForObject("select entity from images where id = 5",
                new RowMapper<Map<String, Object>>() {
                    public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
                        Map<String, Object> results = new HashMap<>();
                        byte[] blobBytes = lobHandler.getBlobAsBytes(rs, "entity");
                        results.put("BLOB", blobBytes);
                        return results;
                    }
                });

        System.out.println(m.get("BLOB"));
    }

    @Test
    public void test_insert_using_spring_2() throws IOException {
        Resource resource = resourceLoader.getResource("img_1.png");

        LobHandler lobHandler = new DefaultLobHandler();
        jdbcTemplate.update("insert into images(id, filename, entity, description) values(?, ?, ?, ?)",
                new PreparedStatementSetter() {
                    @SneakyThrows
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, 4);
                        ps.setString(2, "img_spring.png");
                        lobHandler.getLobCreator().setBlobAsBytes(ps, 3, resource.getInputStream().readAllBytes());
                        ps.setString(4, "nothing to say");
                    }
                });
    }
}

