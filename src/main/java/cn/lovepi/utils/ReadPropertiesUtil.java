package cn.lovepi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesUtil {

    public static String readProp(String key) {
        InputStream in = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream("properties/system.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

}
