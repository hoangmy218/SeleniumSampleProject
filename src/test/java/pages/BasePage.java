package pages;

import driver.DriverFactory;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;

//remove extend DriverFactory
public abstract class  BasePage  {

    protected final WebDriver driver;
    public final int timeOut = DriverFactory.DEFAULT_WAIT;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
    }

    public void navigate(String url) {
        driver.get(url);
    }

    public void goTo(String path) {
        driver.get(DriverFactory.getApplicationInfo().url + path);
    }

    /**
     * Method to wait for an element to be visible
     * @param element element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Timeout: " + timeOut + ".Element is not visible: " + element);
            System.out.println(e.getMessage());
            throw new TimeoutException();
        } catch (Exception e) {
            System.out.println("Timeout or Element issue: " + e.getMessage());
            throw e;
        }
    }

    public boolean waitForElementLocatedToBeVisible(By element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Timeout: " + timeOut + ".Element is not visible: " + element);
            System.out.println(e.getMessage());
            throw new TimeoutException();
        } catch (Exception e) {
            System.out.println("Timeout or Element issue: " + e.getMessage());
            throw e;
        }
    }

    public boolean waitForAllElementToBeVisible(List<WebElement> elementList) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(DriverFactory.LONG_WAIT))
                .pollingEvery(Duration.ofMillis(300));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Timeout: " + DriverFactory.LONG_WAIT + ".Elements are not visible: " + elementList.size() +" - " + elementList);
            System.out.println(e.getMessage());
            throw new TimeoutException();
        } catch (Exception e) {
            System.out.println("Timeout or Element issue: " + e.getMessage());
            throw e;
        }
    }



    public boolean waitForURLChangesToIncludeTargetPath(String path) {
        try {
            wait.until(ExpectedConditions.urlContains(path));
            return true;
        } catch (TimeoutException e) {
            System.out.println("URL does not contain target path");
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    public boolean clearAndType(WebElement element, String string) {
        boolean isVisible = false;
        try {
            isVisible = waitForElementToBeVisible(element);
            if (isVisible) {
                element.clear();
                element.sendKeys(string);
                return true;
            } else {
                System.out.println("Element is visible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getValidationMessageOfField(WebElement field) {
        return field.getAttribute("validationMessage");
    }

    public void clickElement(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }



}
