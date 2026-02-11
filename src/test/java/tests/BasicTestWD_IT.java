package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTestWD_IT extends BaseTest {

    @Test
    public void searchForBlueExample() throws Exception {
        pages().landingPage.goToLandingPage();
//        Assert.assertEquals(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"), true);
        pages().landingPage.searchProduct("Blue");
    }

    @Test
    public void searchForTshirtExample() throws Exception {
        pages().landingPage.goToLandingPage();
//        Assert.assertEquals(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"), true);
        pages().landingPage.searchProduct("Tshirt");
    }

    @Test
    public void checkThatProductPageHasSearchBar() throws Exception {
        pages().landingPage.goToLandingPage();
        Assert.assertEquals(pages().landingPage.sliderIsDisplayed(), true);
//        pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/");
        pages().landingPage.header.goToProductPage();
        Assert.assertEquals(pages().landingPage.searchFieldIsDisplayed(), true);

    }

}
