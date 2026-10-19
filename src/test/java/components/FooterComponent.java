package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class FooterComponent extends BasePage {
    
    public FooterComponent(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "susbscribe_email")
    private WebElement subscribeEmailField;
}
