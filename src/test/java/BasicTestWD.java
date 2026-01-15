import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicTestWD extends DriverFactory {


    private void shopExampleThatSearchesFor (final String searchString) throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://automationexercise.com/products");

//        WebElement searchField = driver.findElement(By.name("search"));
        BaseTest baseTest = new BaseTest();
        baseTest.clearAndType(By.name("search"), searchString);
//        searchField.clear();
//        searchField.sendKeys(searchString);

        System.out.println("URL is: " + driver.getCurrentUrl());

        WebElement searchButton = driver.findElement(By.id("submit_search"));
        searchButton.click();

        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getCurrentUrl().toLowerCase().contains(searchString.toLowerCase());
            }
        });

        System.out.println("URL is: " + driver.getCurrentUrl());
    }

    @Test
    public void searchForBlueExample() throws Exception {
        shopExampleThatSearchesFor("Blue");
    }

    @Test
    public void searchForTshirtExample() throws Exception {
        shopExampleThatSearchesFor("Tshirt");
    }

}
