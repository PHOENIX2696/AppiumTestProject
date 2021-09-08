package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import utils.CommonUtils;
import utils.Constants;
import utils.DriverFactory;
import utils.DriverService;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

/**
 * @author AH19309
 */
public class hooks {

    public static AppiumDriver Android;
    private static AppiumDriver driver = null;
    private static String profile;
    static boolean sessionOpen = false;

    @Before
    public static void setUpDriver(Scenario scn) throws Exception {
        CommonUtils.setFeatureName(scn.getId());
        if (null == driver && !CommonUtils.isApiProfile(profile)) {
            driver = DriverService.getDriver();
            sessionOpen = true;
            Runtime.getRuntime()
                    .addShutdownHook(
                            new Thread() {
                                public void run() {
                                    if (sessionOpen) {
                                        System.out.println("Closing the session...");
                                        driver.terminateApp(Constants.APP_PACKAGE_NAME);
                                        sessionOpen = false;
                                    }
                                }
                            }
                    );
        }
    }

    @Value("${spring.profiles.active}")
    public void setActiveProfile(String activeProfile) {
        profile = activeProfile;
    }

}
