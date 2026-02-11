package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests_IT extends BaseTest {

    @Test
    public void logInToTheWebsite() throws Exception {
        pages().landingPage.goToLandingPage();
//        pages().loginPage.goToLoginPage();
        LoginPage loginPage = pages().landingPage.header.goToLoginPage();
//        pages().loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
//        loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
        loginPage.enterEmail(appInfo.email)
                .enterPassword(appInfo.password)
                .clickLoginBtn();
        pages().landingPage.header.waitUntilUserLink();
        String userLinkText = pages().landingPage.header.getUserLinkText();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

    @Test
    public void logInToTheWebsite3() throws Exception {
        pages().landingPage.goToLandingPage();
        LoginPage loginPage = pages().landingPage.header.goToLoginPage();
//        pages().loginPage.goToLoginPage();
        loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
//        pages().loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
        pages().landingPage.waitForElementToBeVisible(pages().landingPage.header.getUserLink());
        String userLinkText = pages().landingPage.header.getUserLinkText();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

}
