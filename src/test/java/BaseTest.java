import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    BasePage basePage;

    public BaseTest() throws IOException {
//        driver = driver.DriverFactory.getDriver();
        basePage = new BasePage(driver);
    }
}
