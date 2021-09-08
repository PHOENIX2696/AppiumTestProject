package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.restassured.response.Response;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author AH19309
 */
public class DriverFactory {

    private static final ConfigProperties testCp = ConfigProperties.getInstance();
    public static String platform = "";
    private static String[] appName = testCp.getAppPath();
    private static String app_name = appName[0];
    private static String app_install = appName[1];


    public static AppiumDriver<WebElement> getDriver() throws Exception {
        AppiumDriver driver;
        platform = testCp.getDevicePlatformName();
        DesiredCapabilities caps = new DesiredCapabilities();
        if (testCp.getTestEnvironment().equalsIgnoreCase("local")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getAVD());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getLocalDeviceName());
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, testCp.getDevicePlatformName());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getDeviceName());
            caps.setCapability(MobileCapabilityType.VERSION, testCp.getOsVersion());
            caps.setCapability(MobileCapabilityType.APP, app_name);
            System.out.println(String.format("Installing app %s in %s", app_install, testCp.getDeviceName()));
        } else {
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, testCp.getDevicePlatformName());
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getDeviceName());
            caps.setCapability(MobileCapabilityType.VERSION, testCp.getOsVersion());
            caps.setCapability(MobileCapabilityType.APP, app_name);
            caps.setCapability("browserstack.appium_version", "1.21.0");
            caps.setCapability("newCommandTimeout", 180);
            caps.setCapability("wdaConnectionTimeout", 900000);
            caps.setCapability("browserstack.local", false);
            caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
            caps.setCapability("browserstack.debug", true);
            caps.setCapability("browserstack.networkLogs", false);

            System.out.println(String.format("Installing app %s in %s", app_install, testCp.getDeviceName()));
        }

            if (platform.equalsIgnoreCase("android")) {
                driver = setUpAndroidMobileDriver(caps);
            } else {
                throw new Exception("Unknown Platform");
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
            return driver;
    }

    private static AppiumDriver setUpAndroidMobileDriver(DesiredCapabilities caps) throws MalformedURLException, InterruptedException {
        String[] appName = testCp.getAppPath();
        String app_name = appName[0];
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability("compressXml", true);
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);
        caps.setCapability("64bit", true);
        caps.setCapability("browserstack.fullReset", true);
        String iOSVersion = testCp.getOsVersion() != null ? testCp.getOsVersion() : "9.0";
        caps.setCapability("os_version", iOSVersion);
        DesiredCapabilities capss = new DesiredCapabilities();
        if (testCp.getTestEnvironment().equalsIgnoreCase("local")) {
            capss.setCapability("app", app_name);
            capss.setCapability("deviceName", testCp.getDeviceName());
            capss.setCapability("automationName", "uiautomator2");
            capss.setCapability("platformName", "android");
            capss.setCapability("fullReset", true);
            capss.setCapability("compressXml", true);
            capss.setCapability("unicodeKeyboard", true);
            capss.setCapability("resetKeyboard", true);
            capss.setCapability("64bit", true);
        }
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
                new URL(testCp.getServerUrl()),
                testCp.getTestEnvironment().equalsIgnoreCase("local") ? capss : caps
        );
//        if(driver.isAppInstalled(Constants.APP_PACKAGE_NAME)){
//            System.out.println("App is already installed");
//            driver.activateApp(Constants.APP_PACKAGE_NAME);
//            capss.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//        } else {
//            System.out.println(String.format("Installing app %s in %s", app_install, testCp.getDeviceName()));
//        }

        return driver;
    }
}

