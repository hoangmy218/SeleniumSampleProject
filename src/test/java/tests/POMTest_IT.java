package tests;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POMTest_IT extends BaseTest {

    @Test
    public void searchForTshirtExample() throws Exception {
        String keyword = "Tshirt";
        pages().landingPage.goToLandingPage();
        Assert.assertTrue(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"));
        pages().landingPage.searchProduct(keyword);
        Assert.assertTrue(pages().landingPage.hasSearchKeywordInUrl(keyword), "Expected URL to contain keyword " + keyword);

    }

    @Test
    public void checkProductInfoWhenHoverOnProduct() {
        pages().landingPage.goToLandingPage();
        Assert.assertTrue(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"));
        Allure.step("Hover on Product and Check info", () -> {
            pages().landingPage.hoverOnFirstProductItem()
                    .verifyProductOverlayInfo("Blue Top", "Rs. 500")
                    .clickAddToCartWhiteBtn(0);
        });
        Allure.step("Check info cart message", () -> {
            Assert.assertEquals(pages().landingPage.getMessageOnPopup(), "Your product has been added to cart.");
        });
    }
}
