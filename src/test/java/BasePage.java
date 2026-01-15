import driver.DriverFactory;
import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.DriverManager;
import java.time.Duration;

public class BasePage extends DriverFactory {

    protected WebDriver driver;
    public final int timeOut = DriverFactory.implicitWaitTimeInSeconds;

    public BasePage(WebDriver driver) throws IOException {
//        super(driver);
        this.driver = driver;
    }

    public void navigate(String url) {
        driver.get("https://automationexercise.com/");
    }



}
