package components;

import io.qameta.allure.Step;
import model.CardModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class CardComponent extends BasePage {

    public CardComponent(WebDriver driver) throws Exception{
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name_on_card")
    private WebElement cardNameField;
    
    @FindBy(name = "card_number")
    private WebElement cardNumberField;

    @FindBy(name = "cvc")
    private WebElement cardCVCField;
    
    @FindBy(name = "expiry_month")
    private WebElement cardExpMonth;

    @FindBy(name = "expiry_year")
    private WebElement cardExpYear;

    @Step("Fill Card Form {0}")
    public void fillCardForm(CardModel cardForm) {
        cardNameField.sendKeys(cardForm.getCardName());
        cardNumberField.sendKeys(cardForm.getCardNumber());
        cardCVCField.sendKeys(cardForm.getCardCVC().toString());
        cardExpMonth.sendKeys(cardForm.getExpiryMonth().toString());
        cardExpYear.sendKeys(cardForm.getExpiryYear().toString());
    }
}
