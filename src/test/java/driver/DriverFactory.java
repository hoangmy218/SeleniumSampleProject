package driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class DriverFactory {
//    private static List<driver.WebDriverThread> threadList = new ArrayList<driver.WebDriverThread>();
    private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
    private static ThreadLocal<WebDriverThread> driverThread;
    public static Properties credentials;


    //Load System Config from application.properties
    private static Properties loadSystemConfig() {
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("./src/test/resources/configures/application.properties"));
            return pr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setSystemCredentials() {
        Properties property = loadSystemConfig();

        //get credential - system info from systemProperties
        String url = System.getProperty("url") != null ? System.getProperty("url") : property.getProperty("url");
        String username = System.getProperty("username") != null ? System.getProperty("username") : property.getProperty("username");
        String password = System.getProperty("password") != null ? System.getProperty("password") : property.getProperty("password");

        if (!url.endsWith("/")) {
            url += "/";
        }
        credentials = property;
    }

    public static void loadConfig() {
        setSystemCredentials();

        String homeDir = System.getProperty("user.home");
        Path windowsDownloadPath = Paths.get(homeDir,"Downloads");

        String linuxDownloadPath = System.getProperty("user.dir");

        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            // Set windows default download folder
            System.setProperty("downloadFilePath",windowsDownloadPath.toString());
        }
        else if (os.contains("Linux")) {
            // Set linux default download folder
            System.setProperty("downloadFilePath",linuxDownloadPath);
        }
    }


    @BeforeSuite
    public static void instantiateDriverObject() {
        loadConfig();
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getDriver();
    }

    @AfterMethod
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObjects() {
        for (WebDriverThread webDriverThread: webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }

//    public static WebDriver getDriver() {
//        if (driver.get() == null) {
//            WebDriverManager.chromedriver().setup();
//            driver.set(new ChromeDriver());
//        }
//        return driver.get();
//    }
}
