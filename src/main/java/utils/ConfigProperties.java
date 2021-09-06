package utils;

import java.util.Properties;

/**
 * @author AH19309
 */
public class ConfigProperties {

    private static ConfigProperties _instance = null;
    private Properties properties;

    private ConfigProperties() throws RuntimeException {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static ConfigProperties getInstance() {
        if (_instance == null) {
            _instance = new ConfigProperties();
        }
        return _instance;
    }

    public String getTestEnvironment() {
        return properties.getProperty("test_env");
    }

    public String getServerUrl() {
        return properties.getProperty("server_url");
    }

    public String getDevicePlatformName() {
        return properties.getProperty("device_platform");
    }

    public String getDeviceName()
    {
        return properties.getProperty("device_name").replace("_", " ");
    }

    public String getOsVersion() {
        return properties.getProperty("device_os_version");
    }

    public String getAVD(){
        return properties.getProperty("AVD").replace("_", " ");
    }

}
