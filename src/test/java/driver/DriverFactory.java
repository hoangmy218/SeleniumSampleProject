package driver;

import listeners.ScreenshotListener;
import objects.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

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
    private static ThreadLocal<ApplicationProperties> credentials = ThreadLocal.withInitial(() -> null);
    public static int implicitWaitTimeInSeconds = 15;


    //Load System Config from app-test-user.properties
    private static Properties loadSystemConfig() {
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("./src/test/resources/configures/app-test-user.properties"));
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
        String email = System.getProperty("email") != null ? System.getProperty("email") : property.getProperty("email");
        String password = System.getProperty("password") != null ? System.getProperty("password") : property.getProperty("password");

        if (!url.endsWith("/")) {
            url += "/";
        }
        credentials.set(new ApplicationProperties(url, email, password));
    }

    public static ApplicationProperties getApplicationInfo() {
        System.out.println("Print TEST: " + credentials.get());
        return credentials.get();
    }

    public static void clearCredentials() {
        credentials.remove();
    }

    public static void loadConfig() {
//        setSystemCredentials();

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


//    @BeforeSuite
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
        System.out.println("Print TEST WebDriver: " + driverThread.get().getDriver());
        return driverThread.get().getDriver();
    }

//    @AfterMethod
    public static void clearCookies() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

//    @AfterSuite
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
