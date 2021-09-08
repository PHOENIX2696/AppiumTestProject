package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
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
        String userName = "pprag_9yLUNK";
        String userAccessKey = "gSLkqd6z229wfymzFnrC";
        String serverUrl = properties.getProperty("server_url");
        return serverUrl.replace("${AUTO_BS_USER_NAME}", userName).replace("${AUTO_BS_ACCESS_KEY}", userAccessKey);

    }

    public String getDevicePlatformName() {
        return properties.getProperty("device_platform");
    }

    public String getDeviceName()
    {
        return properties.getProperty("device_name").replace("_", " ");
    }

    public String getLocalDeviceName()
    {
        return properties.getProperty("device_name_local").replace("_", " ");
    }

    public String getOsVersion() {
        return properties.getProperty("device_os_version");
    }

    public String getAVD(){
        return properties.getProperty("AVD").replace("_", " ");
    }

    public static Response browserStackRecentApps(String appPath) {
        return RestAssured.given()
                .auth()
                .basic("pprag_9yLUNK", "gSLkqd6z229wfymzFnrC")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/recent_apps/" + appPath);
    }

    public String[] getAppPath() {
        String testEnv = properties.getProperty("test_env");
        String devicePlatfrom = properties.getProperty("device_platform");
        String[] application = new String[2];
        String app_path = properties.getProperty("application_path");
        List<Object> appNamesList;
        List<Object> appUrlList;
        if (testEnv.equalsIgnoreCase("local")) {
            File appFile = new File("App");
            File appName = new File(appFile,"TestApp.apk");
            application[0] = appName.getAbsolutePath();
            application[1] = appName.getAbsolutePath();
            System.out.println("Application[0]" + application[0]);
            System.out.println("Application[1]" + application[1]);
        } else if (devicePlatfrom.equalsIgnoreCase("android")) {
            Response responseAndroidUat = browserStackRecentApps(app_path);
            System.out.println("BrowserStack response" + responseAndroidUat.print());
            appNamesList = responseAndroidUat.jsonPath().getList("app_name");
            System.out.println("App Name" + appNamesList);
            appUrlList = responseAndroidUat.jsonPath().getList("app_url");
            System.out.println("App URL" + appUrlList);
            for (int i = 0; i < appNamesList.size(); i++) {
                String appName = appNamesList.get(i).toString();
                String appUrl = appUrlList.get(i).toString();
                application[0] = appUrl;
                application[1] = appName;
                break;
            }
        } else {
            application[0] = app_path;
            application[1] = app_path;
        }
        return application;
    }
}
