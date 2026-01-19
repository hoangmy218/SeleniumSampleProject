package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public By userLink = By.xpath("//i[contains(@class,'fa-user')]/parent::a");

    public By searchField = By.name("search");

    public By searchBtn = By.id("submit_search");

    public void goToProductPage(WebDriver driver) {
        driver.get("https://automationexercise.com/products");
    }

    public void searchProduct (String searchString, WebDriver driver) throws Exception {

        goToProductPage(driver);
        clearAndType(searchField, searchString);

        System.out.println("URL is: " + driver.getCurrentUrl());

        WebElement searchButton = driver.findElement(searchBtn);
        searchButton.click();

        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getCurrentUrl().equalsIgnoreCase("https://automationexercise.com/products?search=" + searchString);
            }
        });

        System.out.println("URL is: " + driver.getCurrentUrl() + " should contains " + searchString.toLowerCase());
    }





}
