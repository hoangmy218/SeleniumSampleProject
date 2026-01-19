package tests;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LandingPage;
import tests.BaseTest;

import java.io.IOException;
import java.time.Duration;

public class BasicTestWD extends BaseTest {
    LandingPage landingPage;

    public BasicTestWD() throws Exception {
        landingPage = new LandingPage(driver);
    }


    public void shopExampleThatSearchesFor (String searchString) throws Exception {
        WebDriver driver = DriverFactory.getDriver();

//        System.out.println("Print TEST URL after getDriver is: " + driver.getCurrentUrl());
        landingPage.goToProductPage(driver);
//        WebDriver driver = DriverFactory.getDriver();
//
//        driver.get("https://automationexercise.com/products");

//        WebElement searchField = driver.findElement(By.name("search"));
//        BaseTest baseTest = new BaseTest();
        clearAndType(landingPage.searchField, searchString, driver);
//        searchField.clear();
//        searchField.sendKeys(searchString);

        System.out.println("URL is: " + driver.getCurrentUrl());

        WebElement searchButton = driver.findElement(By.id("submit_search"));
        searchButton.click();

        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getCurrentUrl().equalsIgnoreCase("https://automationexercise.com/products?search=" + searchString);
            }
        });

        System.out.println("URL is: " + driver.getCurrentUrl() + " should contains " + searchString.toLowerCase());
    }

    @Test
    public void searchForBlueExample() throws Exception {
        shopExampleThatSearchesFor("Blue");
    }

    @Test
    public void searchForTshirtExample() throws Exception {
        shopExampleThatSearchesFor("Tshirt");
    }

//    @Test
//    public void searchForBlue() throws Exception {
//        landingPage.searchProduct("Blue", driver);
//    }
//
//    @Test
//    public void searchForTshirt() throws Exception {
//        landingPage.searchProduct("Tshirt", driver);
//    }

}
