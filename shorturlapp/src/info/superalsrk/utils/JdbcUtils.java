package info.superalsrk.utils;

import org.apache.commons.dbcp.BasicDataSource;  
import org.apache.commons.dbcp.BasicDataSourceFactory;  
  
import java.sql.SQLException;  
import java.sql.Connection;  
import java.util.Properties;  
  
public class JdbcUtils {  
    private static BasicDataSource dataSource = null;  
  
    public JdbcUtils() {  
    }  
  
    public static void init() {  
  
        if (dataSource != null) {  
            try {  
                dataSource.close();  
            } catch (Exception e) {  
                //  
            }  
            dataSource = null;  
        }  
  
        try {  
            Properties p = new Properties();  
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");  
            p.setProperty("url", "jdbc:MySQL://127.0.0.1:3306/test");  
            p.setProperty("password", "1234");  
            p.setProperty("username", "omoika");  
            p.setProperty("maxActive", "30");  
            p.setProperty("maxIdle", "10");  
            p.setProperty("maxWait", "1000");  
            p.setProperty("removeAbandoned", "false");  
            p.setProperty("removeAbandonedTimeout", "120");  
            p.setProperty("testOnBorrow", "true");  
            p.setProperty("logAbandoned", "true");  
  
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);  
  
        } catch (Exception e) {  
            //  
        }  
    }  
  
  
    public static synchronized Connection getConnection() throws  SQLException {  
        if (dataSource == null) {  
            init();  
        }  
        Connection conn = null;  
        if (dataSource != null) {  
            conn = dataSource.getConnection();  
        }  
        return conn;  
    }  
}  