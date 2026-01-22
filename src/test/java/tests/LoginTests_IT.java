package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests_IT extends BaseTest {

    @Test
    public void logInToTheWebsite() throws Exception {
        pages().loginPage.goToLoginPage();
        pages().loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
        pages().landingPage.waitUntilUserLink();
        String userLinkText = pages().landingPage.getUserLink().getText().trim();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

    @Test
    public void logInToTheWebsite3() throws Exception {
        pages().loginPage.goToLoginPage();
        pages().loginPage.loginWithUsernameAndPassword(appInfo.email, appInfo.password);
        pages().landingPage.waitForElementToBeVisible(pages().landingPage.userLink);
        pages().landingPage.waitUntilUserLink();
        String userLinkText = pages().landingPage.getUserLink().getText().trim();
        Assert.assertEquals(userLinkText, "Logged in as My");
    }

}
