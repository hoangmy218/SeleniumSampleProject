package pages;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LandingPage extends BasePage {

    public final PageHeader header;

    public LandingPage(WebDriver driver) throws Exception {
        super(driver);
        header = new PageHeader(driver);
        //Debug
//        System.out.println("Landing Page Thread: " + Thread.currentThread().getName() + " | Driver: " + driver.hashCode());
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "search")
    private WebElement searchField;
    
    @FindBy(id = "submit_search")
    private WebElement searchBtn;
    
    @FindBy(id = "slider-carousel")
    private List<WebElement> slider;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productItem;

    public void searchProduct(String searchString) throws Exception {
        header.goToProductPage();
        Assert.assertEquals(isCurrentURLCorrect("https://automationexercise.com/products"), true);
        clearAndType(searchField, searchString);
        searchBtn.click();
        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getCurrentUrl().equalsIgnoreCase("https://automationexercise.com/products?search=" + searchString);
            }
        });
//        System.out.println("URL is: " + driver.getCurrentUrl() + " should contains " + searchString.toLowerCase());
    }

    public boolean isCurrentURLCorrect(String url) {
        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getCurrentUrl().equalsIgnoreCase(url);
            }
        });
        String currentUrl =  driver.getCurrentUrl();
        Boolean isCurrentURL = currentUrl.equalsIgnoreCase(url);
//        System.out.println("URL is: " + currentUrl + " should equal " + url + " " + isCurrentURL);
        return isCurrentURL;
    }

    public void goToLandingPage() {
        String domain = DriverFactory.getApplicationInfo().url;
        navigate(domain);
        //System.out.println("Page GTLP Thread: " + Thread.currentThread().getName() + " | Driver: " + driver.hashCode());
        System.out.println("domain: " + domain);
        Assert.assertEquals(isCurrentURLCorrect("https://automationexercise.com/"), true);
    }

    //Verify slider exist in DOM, not throw exception if not
    public boolean sliderIsDisplayed() {
        return slider.size() == 1;
    }

    //Verify search field display on UI, throw exception if element not exist in DOM
    public boolean searchFieldIsDisplayed() {
        return searchField.isDisplayed();
    }






}
