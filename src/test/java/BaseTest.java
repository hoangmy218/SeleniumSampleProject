import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    BasePage basePage;

    public BaseTest() throws Exception {
        driver = DriverFactory.getDriver();
        basePage = new BasePage(driver);
    }

    /**
     * Method to wait for an element to be visible
     * @param element element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForElementToBeVisible(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(basePage.timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + element);
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    public boolean clearAndType(By element, String string) {
        boolean isVisible = false;
        try {
            isVisible = waitForElementToBeVisible(element);
            if (isVisible) {
                WebElement searchField = driver.findElement(element);
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
