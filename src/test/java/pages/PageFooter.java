package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFooter extends BasePage{
    
    public PageFooter(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "susbscribe_email")
    private WebElement subscribeEmailField;
}
