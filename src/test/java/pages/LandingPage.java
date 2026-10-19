package pages;

import components.HeaderComponent;
import driver.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class LandingPage extends BasePage {

    public final HeaderComponent header;

    public LandingPage(WebDriver driver) throws Exception {
        super(driver);
        header = new HeaderComponent(driver);
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

    @FindBy(css = ".single-products")
    private WebElement firstProductItem;
    
    @FindBy(xpath = "//div[@class='product-overlay']")
    private List<WebElement> productOverlayItems;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]/a[text()=\"Add to cart\"]")
    private List<WebElement> addToCartWhiteBtn;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]//p")
    private List<WebElement> productNamesOnProductOverlayItems;

    @FindBy(xpath = "(//div[@class='overlay-content']//p)[1]")
    private  WebElement firstProduct;
    
    @FindBy(xpath = "//div[@class=\"overlay-content\"]/h2")
    private List<WebElement> productPriceOnProductOverlayItems;

    @FindBy(xpath = "//div[@id=\"cartModal\"]//p[@class=\"text-center\"][1]")
    private WebElement cartContent;

    @Step("Search for product with keyword {0}")
    public void searchProduct(String searchString) throws Exception {
        header.goToProductPage();
        waitForURLChangesToIncludeTargetPath("/products");
        clearAndType(searchField, searchString);
        searchBtn.click();
        //Wait until URL has change
        waitForURLChangesToIncludeTargetPath(searchString);
    }

    public boolean isCurrentURLCorrect(String url) {
//        (new WebDriverWait(driver, Duration.ofSeconds(3))).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driverObject) {
//                return driverObject.getCurrentUrl().equalsIgnoreCase(url);
//            }
//        });
        String currentUrl =  driver.getCurrentUrl();
        Boolean isCurrentURL = currentUrl.equalsIgnoreCase(url);
//        System.out.println("URL is: " + currentUrl + " should equal " + url + " " + isCurrentURL);
        return isCurrentURL;
    }

    @Step("Go to Landing Page")
    public void goToLandingPage() {
        String domain = DriverFactory.getApplicationInfo().url;
        navigate(domain);
        //System.out.println("Page GTLP Thread: " + Thread.currentThread().getName() + " | Driver: " + driver.hashCode());
        System.out.println("domain: " + domain);
    }

    //Verify slider exist in DOM, not throw exception if not
    public boolean sliderIsDisplayed() {
        return slider.size() == 1;
    }

    //Verify search field display on UI, throw exception if element not exist in DOM
    public boolean searchFieldIsDisplayed() {
        return searchField.isDisplayed();
    }
    
    public WebElement getProductItem(int no) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        waitForAllElementToBeVisible(productItems);
        return productItems.get(no);
    }
    
    public LandingPage hoverProductItem(int no) {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(getProductItem(no)).perform();

        return this;
    }

    public LandingPage hoverOnFirstProductItem() {
        Actions advancedActions = new Actions(driver);
        advancedActions.moveToElement(firstProductItem).perform();

        return this;
    }

    public LandingPage scrollToFirstProduct(int no) {
        new Actions(driver).scrollToElement(getProductItem(no)).perform();
        //        // 2. Scroll element into the center of the viewport (prevents sticky headers from obscuring it)
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", getProductItem(no));
        return this;
    }

    public LandingPage scrollToAddToCartButton(int no) {
//        new Actions(driver).scrollToElement(getProductItem(no)).perform();
        //        // 2. Scroll element into the center of the viewport (prevents sticky headers from obscuring it)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", getAddToCartWhiteBtn(no));
        return this;
    }


    private final By firstOverlayProductName = By.xpath("(//div[@class='overlay-content']//p)[1]");

    public LandingPage verifyProductOverlayInfo(String productName, String productPrice) {
        // 1. Wait until at least one element in the list is visible
//        wait = WebDriverWait(driver,15,5)
//        wait.until(ExpectedConditions.vis(productNamesOnProductOverlayItems));


//        System.out.println("productNamesOnProductOverlayItems[0]: " + firstOverlayProductName );
        waitForElementLocatedToBeVisible(firstOverlayProductName);
        WebElement firstProductOverlay = productNamesOnProductOverlayItems.get(0);
        Assert.assertEquals(firstProductOverlay.getText(), productName);
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

    public String getMessageOnPopup() {
        waitForElementToBeVisible(cartContent);
        return cartContent.getText();
    }

    @Step("Check URL contain Keyword")
    public boolean hasSearchKeywordInUrl(String keyword) {
        if (keyword ==null || keyword.isEmpty()) {
            return false;
        }
        String currentUrl =  driver.getCurrentUrl();
        return currentUrl != null && currentUrl.equalsIgnoreCase("https://automationexercise.com/products?search=" + keyword);
    }


    public WebElement getAddToCartWhiteBtn(int no) {
        return addToCartWhiteBtn.get(no);
    }








}
