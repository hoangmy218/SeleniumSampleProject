package driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static driver.DriverType.CHROME;
import static driver.DriverType.valueOf;

public class WebDriverThread {
    private WebDriver webDriver;
    private DriverType selectedDriverType;
    private final DriverType defaultDriverType = CHROME;
    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    public WebDriver getDriver() throws Exception {
        if (webDriver == null) {
            selectedDriverType = determineEffectiveDriveType();
            instantiateWebDriver();
        }
        return webDriver;
    }

    private void instantiateWebDriver() throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");
        webDriver = selectedDriverType.getWebDriverObject();
        maximizeWindow(webDriver);
    }

    private void maximizeWindow(WebDriver webDriver) {
        webDriver.manage().window().maximize();
    }

    private DriverType determineEffectiveDriveType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        return driverType;
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
