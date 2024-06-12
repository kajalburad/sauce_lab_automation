package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PasswordManager {



    private static final String CONFIG_FILE = "properties/Test.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            // Get the current directory
            String currentDirectory = System.getProperty("user.dir");

            // Construct the absolute path to the properties file
            String filePath = currentDirectory + "//" + CONFIG_FILE;
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPassword(String key) {
        return properties.getProperty(key);
    }
}
