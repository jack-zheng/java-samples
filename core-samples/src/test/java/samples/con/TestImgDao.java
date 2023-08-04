package samples.con;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class TestImgDao extends JdbcDaoSupport {

    public int countRow() {
        return getJdbcTemplate().queryForObject("select count(*) from images;", Integer.class);
    }

    // 不需要 set 开头，直接用注解就行。估计是容器生成实例之后，看有这个注解就调用了这个方法。
    @Autowired
    public void injectDataSource(DataSource source) {
        setDataSource(source);
    }
}
