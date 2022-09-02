package student.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接池工具类
 */
public class DruidUtil {
    private static DataSource dataSource = null;
    static {
        try {
            Properties pro = new Properties();
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"); // 获取配置文件输入流
            pro.load(is); // 加载配置文件
            dataSource = DruidDataSourceFactory.createDataSource(pro); // 获取连接池对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取Connection对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
