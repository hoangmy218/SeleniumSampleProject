package tests;

import driver.DriverFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    LandingPage landingPage;

    public LoginTests() throws Exception {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
    }

//    LoginPage loginPage;


//    public LoginTests() throws Exception {
//        loginPage = new LoginPage(DriverFactory.getDriver());
//    }

    @Test
    public void logInToTheWebsite() throws Exception {
        WebDriver driver = DriverFactory.getDriver();
        loginPage.goToLoginPage(driver);
        loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password, driver);
        waitForElementToBeVisible(landingPage.userLink, driver);
        String userLinkText = driver.findElement(landingPage.userLink).getText().trim();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

    @Test
    public void logInToTheWebsite2() throws Exception {
        WebDriver driver = DriverFactory.getDriver();
        loginPage.goToLoginPage(driver);
        loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password, driver);
        waitForElementToBeVisible(landingPage.userLink, driver);
        String userLinkText = driver.findElement(landingPage.userLink).getText().trim();
        Assert.assertEquals(userLinkText, "Logged in as My ");
    }
}
