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

import java.time.Duration;

@Listeners(ScreenshotListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected ApplicationProperties appInfo;
    BasePage basePage;

    @BeforeSuite
    public void globalSetup() {
        DriverFactory.instantiateDriverObject();
    }

    @BeforeMethod
    public void setUp() throws Exception{
        driver = DriverFactory.getDriver();
        DriverFactory.setSystemCredentials();
        appInfo = DriverFactory.getApplicationInfo();
        basePage = new BasePage(driver);
    }

    @AfterMethod
    public void teardown() throws Exception{
        DriverFactory.clearCookies();
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
    public boolean waitForElementToBeVisible(By element, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(basePage.timeOut));
            System.out.println("Driver info: " + driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + element);
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    public boolean clearAndType(By element, String string, WebDriver driver) {
        boolean isVisible = false;
        try {
            isVisible = waitForElementToBeVisible(element, driver);
            if (isVisible) {
                WebElement searchField = driver.findElement(element);
                System.out.println("Print TEST " +
                        Thread.currentThread().getId() + " -> " + driver
                );
                searchField.clear();
                searchField.sendKeys(string);
                return true;
            } else {
                System.out.println("Element is visible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
