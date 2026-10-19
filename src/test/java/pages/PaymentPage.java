package pages;

import components.CardComponent;
import io.qameta.allure.Step;
import model.CardModel;
import model.PaymentModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage{

    public final CardComponent cardDetailsForm;

    public PaymentPage(WebDriver driver) throws Exception {
        super(driver);
        // Locate section container nodes on the page
        //E.g addressDetailsForm = new AddressComponent(driver)
        cardDetailsForm = new CardComponent(driver);

        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = "[data-qa='pay-button']")
    private WebElement payBtn;

    @FindBy(css = "[data-qa='order-placed']")
    private WebElement orderPlacedTxt;


    @Step("Go to Payment Page")
    public void goToPaymentPage() {
        goTo("/payment");
    }

    @Step("Fill Payment Form")
    public PaymentPage fillPaymentForm(PaymentModel paymentFormData) {
        //Delegate work to each component using data from the DTO
        cardDetailsForm.fillCardForm(paymentFormData.getCard());
        return this;
    }

    @Step("Submit Payment Form")
    public PaymentPage submitPayment() {
        clickElement(payBtn);
        return this;
    }

    public boolean isPaymentDone() {
        waitForElementToBeVisible(orderPlacedTxt);
        if (orderPlacedTxt != null && orderPlacedTxt.getText().equalsIgnoreCase("Order Placed!"))
            return true;
        return false;
    }


}
