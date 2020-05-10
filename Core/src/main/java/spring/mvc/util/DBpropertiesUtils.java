package spring.mvc.util;

import java.io.IOException;
import java.util.Properties;
public class DBpropertiesUtils {

    // 单例模式
    private static DBpropertiesUtils instance;
    // prop对象
    private static Properties properties;

    private DBpropertiesUtils() {}

    public static DBpropertiesUtils getInstance() {
        if (instance == null) {
            instance = new DBpropertiesUtils();
            properties = new Properties();
            try {
                properties.load(DBpropertiesUtils.class.getResourceAsStream("/db.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}