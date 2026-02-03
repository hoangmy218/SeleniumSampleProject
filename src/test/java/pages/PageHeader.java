package pages;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        productLink.click();
        blockAds();
    }

    public void blockAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //blocking ads
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
        js.executeScript("window.scrollBy(0,350)");
        System.out.println("Blocked Ads");
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
