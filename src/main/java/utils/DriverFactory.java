package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author AH19309
 */
public class DriverFactory {

    private static final ConfigProperties testCp = ConfigProperties.getInstance();
    public static String platform = "";


    public static AppiumDriver<WebElement> getDriver() throws Exception {
        AppiumDriver driver;
        platform = testCp.getDevicePlatformName();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, testCp.getDevicePlatformName());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getDeviceName());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getOsVersion());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, testCp.getAVD());

        if (platform.equalsIgnoreCase("android")) {
            driver = setUpAndroidMobileDriver(caps);
        } else {
            throw new Exception("Unknown Platform");
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
        return driver;
    }

    private static AppiumDriver setUpAndroidMobileDriver(DesiredCapabilities caps) throws MalformedURLException {
        DesiredCapabilities capss = new DesiredCapabilities();
        if (testCp.getTestEnvironment().equalsIgnoreCase("local")) {
            capss.setCapability("autoAcceptAlerts", true);
            capss.setCapability("unicodeKeyboard", true);
            capss.setCapability("resetKeyboard", true);
            capss.setCapability("noReset", true);
            capss.setCapability(MobileCapabilityType.APPLICATION_NAME, "Aptoide");
            capss.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cm.aptoide.pt");
            capss.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cm.aptoide.pt.view.MainActivity");
            capss.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        }
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
                new URL(testCp.getServerUrl()),
                testCp.getTestEnvironment().equalsIgnoreCase("local") ? capss : caps
        );
        return driver;
    }
}

