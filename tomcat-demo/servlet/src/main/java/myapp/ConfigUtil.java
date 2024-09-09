package myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();

    static {
        // 读取 resources/config.properties 文件
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Could not read config file");
            }
            // 加载 properties 文件
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Could not read config file", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
