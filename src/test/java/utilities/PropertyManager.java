package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static Properties instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+
            "/src/test/java/resources/testconfig.properties";


    //Create a Singleton instance.
    public static Properties getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new Properties();
                //Read testconfig.properties file
                try {
                    instance.load(new FileInputStream(propertyFilePath));


                } catch (IOException e) {
                    System.out.println("Configuration properties file cannot be found @ " + propertyFilePath);
                }
            }
        }
        return instance;
    }
}
