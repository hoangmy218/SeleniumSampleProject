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


    /**
     * Method to wait for an element to be visible
     * @param element element to be visible
     * @return true if element is visible else throws TimeoutException
     */
//    public boolean waitForElementToBeVisible(By element, WebDriver driver) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DriverFactory.implicitWaitTimeInSeconds));
//            System.out.println("Driver info: " + driver);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//            return true;
//        } catch (TimeoutException e) {
//            System.out.println("Element is not visible: " + element);
//            System.out.println(e.getMessage());
//            throw new TimeoutException();
//        }
//    }
//
//    public boolean waitForElementToBeVisible(By element) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(pa.timeOut));
//            System.out.println("Driver info: " + driver);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//            return true;
//        } catch (TimeoutException | IOException e) {
//            System.out.println("Element is not visible: " + element);
//            System.out.println(e.getMessage());
//            throw new TimeoutException();
//        }
//    }
//
//    public boolean clearAndType(By element, String string, WebDriver driver) {
//        boolean isVisible = false;
//        try {
//            isVisible = waitForElementToBeVisible(element, driver);
//            if (isVisible) {
//                WebElement searchField = driver.findElement(element);
//                System.out.println("Print TEST clearAndType w Driver " +
//                        Thread.currentThread().getId() + " -> " + driver
//                );
//                searchField.clear();
//                searchField.sendKeys(string);
//                return true;
//            } else {
//                System.out.println("Element is visible");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
