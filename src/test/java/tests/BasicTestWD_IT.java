package tests;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTestWD_IT extends BaseTest {

//    @Test
//    public void searchForBlueExample() throws Exception {
//        pages().landingPage.goToLandingPage();
////        Assert.assertEquals(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"), true);
//        pages().landingPage.searchProduct("Blue");
//        pages().landingPage.verifyURLContainSearchKey("Blue");
//    }


//    @Test
    public void searchForBlueExample() throws Exception {
        navigateToLandingPage();
        searchProductByName("Blue");
        verifyURLHasChangeAndContainKeyName(("Blue"));
    }

//    @Test
    public void searchForTshirtExample() throws Exception {
        navigateToLandingPage();
        searchProductByName("Tshirt");
        verifyURLHasChangeAndContainKeyName(("Tshirt"));
    }

    @Step("Navigate to Landing page")
    public void navigateToLandingPage() {
        pages().landingPage.goToLandingPage();
    }

    @Step("Search Product by {keyName}")
    public void searchProductByName(String keyName)  throws Exception {
        pages().landingPage.searchProduct(keyName);
    }

    @Step("Verify URL has change and contain {keyName}")
    public void verifyURLHasChangeAndContainKeyName(String keyName) {
//        pages().landingPage.verifyURLContainSearchKey(keyName);
    }

//    @Test
//    public void searchForTshirtExample() throws Exception {
//        pages().landingPage.goToLandingPage();
////        Assert.assertEquals(pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/"), true);
//        pages().landingPage.searchProduct("Tshirt");
//    }

//    @Test
    public void checkThatProductPageHasSearchBar() throws Exception {
        pages().landingPage.goToLandingPage();
        Assert.assertEquals(pages().landingPage.sliderIsDisplayed(), true);
//        pages().landingPage.isCurrentURLCorrect("https://automationexercise.com/");
        pages().landingPage.header.goToProductPage();
        Assert.assertEquals(pages().landingPage.searchFieldIsDisplayed(), true);
    }

//    @Test
//    public void checkHoverOverProductItemAndNavigateToProductDetailsPage() {
//        pages().landingPage.goToLandingPage();
//        pages().landingPage.hoverProductItem(0)
//                .verifyProductOverlayInfo("Blue Top", "Rs. 500")
//                .clickAddToCartWhiteBtn(0)
//                .verifyAddToCartSuccess("Your product has been added to cart.");
//    }

    @Test
    public void checkHoverOverProductItemAndNavigateToProductDetailsPage() {
        navigateToLandingPage();
        checkHoverOverProductItem("Blue Top", "Rs. 500");
    }

    @Step
    public void checkHoverOverProductItem(String productName, String productPrice) {
        pages().landingPage
                .scrollToFirstProduct(0)
                .hoverProductItem(0)
                .verifyProductOverlayInfo(productName, productPrice);
    }

    @Step
    public void verify(String productName, String productPrice) {
        pages().landingPage.hoverProductItem(0)
                .verifyProductOverlayInfo(productName, productPrice)
                .clickAddToCartWhiteBtn(0)
                .verifyAddToCartSuccess("Your product has been added to cart.");
    }

}
