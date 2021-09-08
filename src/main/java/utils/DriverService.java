package utils;

import io.appium.java_client.AppiumDriver;

/**
 * @author AH19309
 */
public class DriverService {

    public static String platform = "";
    private static AppiumDriver _driver = null;

    public static AppiumDriver getDriver() throws Exception {
        if (null == _driver && !platform.equalsIgnoreCase(EnumConstants.Platform.api.toString())) {
            _driver = DriverFactory.getDriver();
        }
        return _driver;
    }
}
