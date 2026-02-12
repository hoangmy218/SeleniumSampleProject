package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

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

    @FindBy(xpath = "//div[@class='single-products']")
    private List<WebElement> productItems;
    
    @FindBy(xpath = "//div[@class='product-overlay']")
    private List<WebElement> productOverlayItems;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]/a[text()=\"Add to cart\"]")
    private List<WebElement> addToCartWhiteBtn;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]//p")
    private List<WebElement> productNamesOnProductOverlayItems;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]/h2")
    private List<WebElement> productPriceOnProductOverlayItems;

    @FindBy(xpath = "//div[@id=\"cartModal\"]//p[@class=\"text-center\"][1]")
    private WebElement cartContent;

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
    
    public WebElement getFirstProductItem() {
        return productItems.get(0);
    }
    
    public LandingPage hoverProductItem(int no) {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(productItems.get(no)).perform();
        return this;
    }

    public LandingPage verifyProductOverlayInfo(String productName, String productPrice) {
        WebElement actualProName = productNamesOnProductOverlayItems.get(0);
        waitForElementToBeVisible(actualProName);
        Assert.assertEquals(actualProName.getText(), productName);
        WebElement actualProductPrice = productPriceOnProductOverlayItems.get(0);
        waitForElementToBeVisible(actualProductPrice);
        Assert.assertEquals(actualProductPrice.getText(), productPrice);
        getAddToCartWhiteBtn(0).isDisplayed();
        return this;
    }

    public LandingPage clickAddToCartWhiteBtn(int no) {
        clickElement(getAddToCartWhiteBtn(no));
        return this;
    }

    public LandingPage verifyAddToCartSuccess(String successMsg) {
        waitForElementToBeVisible(cartContent);
        Assert.assertEquals(cartContent.getText(), successMsg);
        return this;
    }


    public WebElement getAddToCartWhiteBtn(int no) {
        return addToCartWhiteBtn.get(no);
    }






}
