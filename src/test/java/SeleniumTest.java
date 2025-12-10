import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SeleniumTest extends BaseTest {

    public SeleniumTest() throws IOException {
        super();
    }

    public void navigateToDashboardPage() {
        driver.get("https://automationexercise.com/");
    }

//    public static void main(String[] args) throws IOException {
//        SeleniumTest test = new SeleniumTest();
//        test.navigateToDashboardPage();
//    }

//    @BeforeTest
//    static void setup() throws IOException{
//        SeleniumTest test = new SeleniumTest();
//    }
//
//    @AfterTest
//    void teardown() {
//        driver.quit();
//    }
//
//    @Test
//    void test() {
//        navigateToDashboardPage();
//        String title = driver.getTitle();
//        // Verify
//        assert(title).contains("Automation Exercise");
//    }
}
