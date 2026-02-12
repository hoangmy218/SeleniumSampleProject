package pages;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PageHeader extends BasePage{

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productLink;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(xpath = "//i[contains(@class, 'fa-user')]/parent::a")
    private WebElement userLink;

    public PageHeader(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToProductPage() {
        clickElement(productLink);
        blockAds();
        //After remove Ads, the actually still not be navigate to Product Page.
        //So we should click again
        clickElement(productLink);
    }



    public LoginPage goToLoginPage() throws IOException {
        clickElement(loginLink);
        return new LoginPage(driver);
    }
    
    @FindBy(xpath = "//*[contains(@class,'adsbygoogle') and contains(@class,'adsbygoogle-noablate')]")
    private List<WebElement> googleAds;

    public void blockAds() {
        //blocking ads
        if (googleAds.size() > 0) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
            js.executeScript("window.scrollBy(0,350)");
            System.out.println("Blocked Ads");
        }
    }

    public String getUserLinkText() {
        return userLink.getText().trim();
    }

    public WebElement getUserLink() {
        return userLink;
    }

    public void waitUntilUserLink(){
        waitForElementToBeVisible(userLink);
    }
}
