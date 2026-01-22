package pages;

import driver.DriverFactory;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.DriverManager;
import java.time.Duration;

//remove extend DriverFactory
public abstract class  BasePage  {

    protected final WebDriver driver;
    public final int timeOut = DriverFactory.implicitWaitTimeInSeconds;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) throws IOException {
//        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
    }

    public void navigate(String url) {
        driver.get(url);
        System.out.println("print TEST URL is: " + driver.getCurrentUrl());
    }

    /**
     * Method to wait for an element to be visible
     * @param element element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForElementToBeVisible(By element) {
        try {
            System.out.println(
                    "[BasePAGE] W4E Thread=" + Thread.currentThread().getId() +
                            " DriverHash=" + System.identityHashCode(driver)
            );

//            System.out.println("Driver Visible info: " + driver);
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
                System.out.println(
                        "[BasePAGE] C&T Thread=" + Thread.currentThread().getId() +
                                " DriverHash=" + System.identityHashCode(driver)
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
