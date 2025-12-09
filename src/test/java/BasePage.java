import org.openqa.selenium.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.sql.DriverManager;

public class BasePage extends Assertion {

    protected WebDriver driver;

    public BasePage(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
    }

    public void navigate(String url) {
        driver.get("https://automationexercise.com/");
    }

}
