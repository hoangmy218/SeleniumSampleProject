package tests;

import org.testng.annotations.Test;

public class BasicTestWD_IT extends BaseTest {

    @Test
    public void searchForBlueExample() throws Exception {
        pages().landingPage.searchProduct("Blue");
    }

    @Test
    public void searchForTshirtExample() throws Exception {
        pages().landingPage.searchProduct("Tshirt");
    }

}
