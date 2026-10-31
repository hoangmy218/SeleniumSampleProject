package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests_IT extends BaseTest {

    @Test
    public void logInToTheWebsite() throws Exception {
        pages().landingPage.goToLandingPage();
        LoginPage loginPage = pages().landingPage.header.goToLoginPage();
        loginPage.enterEmail(appInfo.email)
                .enterPassword(appInfo.password)
                .andSuccessfullyLogin();
        pages().landingPage.header.waitUntilUserLink();
        String userLinkText = pages().landingPage.header.getUserLinkText();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

    @Test
    public void logInToTheWebsiteFail() throws Exception {
        pages().landingPage.goToLandingPage();
        LoginPage loginPage = pages().landingPage.header.goToLoginPage();
        loginPage.enterEmail(appInfo.email)
                .andFailLogin();
        Assert.assertEquals(pages().loginPage.getValidationMessageOfPasswordField(), "Please fill out this field.");
    }

}
