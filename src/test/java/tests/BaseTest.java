package tests;

import driver.DriverFactory;
import listeners.ScreenshotListener;
import objects.ApplicationProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Pages;

import java.io.IOException;
import java.time.Duration;

@Listeners(ScreenshotListener.class)
public class BaseTest {

    protected ApplicationProperties appInfo;

    @BeforeSuite
    public void globalSetup() {
        DriverFactory.loadConfig();
        DriverFactory.instantiateDriverObject();
    }

    protected Pages pages() {
        return Pages.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception{
        WebDriver driver = DriverFactory.createDriver();
        Pages.init(driver);
        DriverFactory.setSystemCredentials();
        appInfo = DriverFactory.getApplicationInfo();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() throws Exception{
        DriverFactory.clearCookies();
        Pages.cleanup();
    }

    @AfterSuite
    public void closeDriver() {
        DriverFactory.closeDriverObjects();
    }
}
