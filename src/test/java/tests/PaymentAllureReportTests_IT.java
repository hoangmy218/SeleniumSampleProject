package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import driver.JSONDataLoader;
import io.qameta.allure.*;
import model.CardModel;
import model.PaymentModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PaymentAllureReportTests_IT extends BaseTest {

    @DataProvider(name = "paymentFormData")
    public Object[][] getPaymentFormData() throws IOException {
        // Reads JSON files from src/test/resources/testdata/
        File jsonFile = new File("src/test/resources/testdata/valid_payments.json");
        ObjectMapper mapper = new ObjectMapper();
        PaymentModel[] validPaymentArray = mapper.readValue(jsonFile, PaymentModel[].class);

        return new Object[][] {
                { "TC_PM_04: Valid card - Expiring late", validPaymentArray[0]},
                { "TC_PM_05: Valid card - Expiring soon", validPaymentArray[1]}
        };
    }

    @Test(dataProvider = "paymentFormData")
    @Description("This test is negative case")
    @Severity(SeverityLevel.CRITICAL) //“trivial”, “minor”, “normal”, “critical”, and “blocker”.
    @Owner("My Ngo")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AT-100")
    @TmsLink("AT-TR-50")
    public void verifyOrderPlaceSuccessWithValidCard(String testCaseId, PaymentModel paymentData) {
        Allure.label("parentSuite", "Tests for Integration");
        Allure.suite("Tests for payment feature");
        Allure.label("subSuite", "Tests for main flows - happy cases");
        // Update Allure Report title dynamically with the ID
        Allure.getLifecycle().updateTestMetadata(test -> test.setDescription(testCaseId));

        pages().paymentPage.goToPaymentPage();
        pages().paymentPage.fillPaymentForm(paymentData)
                .submitPayment();
        Allure.step("Verify message match 'Order Placed!' when payment done", () -> {
            Assert.assertEquals(pages().paymentPage.getTextOnPaymentPopup(), "ORDER PLACED!", "Message text");
        });
    }

    @Test
    @Description("This test is negative case")
    @Epic("UI")
    @Feature("Payment feature")
    @Story("Missing field")
    @Severity(SeverityLevel.NORMAL) //“trivial”, “minor”, “normal”, “critical”, and “blocker”.
    @Owner("My Ngo")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AT-101")
    @TmsLink("AT-TR-51")
    public void verifyValidationMessageOnCardNameField() {
        PaymentModel missingCardNameForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);
        missingCardNameForm.getCard().setCardName("");

        pages().paymentPage.goToPaymentPage();
        pages().paymentPage.fillPaymentForm(missingCardNameForm)
                .submitPayment();
        Allure.step("Verify display 'Please fill out this field.' message when submitting", () -> {
            Assert.assertEquals(pages().paymentPage.cardDetailsForm.getCardNameValidationMessage(), "Please fill out this field.");
        });
    }

    @Test
    @Description("This test is negative case")
    @Epic("UI")
    @Feature("Payment feature")
    @Story("Missing field")
    @Severity(SeverityLevel.NORMAL) //“trivial”, “minor”, “normal”, “critical”, and “blocker”.
    @Owner("My Ngo")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AT-102")
    @TmsLink("AT-TR-52")
    public void verifyValidationMessageOnCardNumberField() {
        PaymentModel missingCardNumberForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);
        missingCardNumberForm.getCard().setCardNumber("");

        pages().paymentPage.goToPaymentPage();
        pages().paymentPage.fillPaymentForm(missingCardNumberForm)
                .submitPayment();
        Allure.step("Verify display 'Please fill out this field.' message when submitting", () -> {
            Assert.assertEquals(pages().paymentPage.cardDetailsForm.getCardNumberValidationMessage(), "Please fill out this field.");
        });
    }

    @Test
    @Description("This test is negative case")
    @Epic("UI")
    @Feature("Payment feature")
    @Story("Missing field")
    @Severity(SeverityLevel.NORMAL) //“trivial”, “minor”, “normal”, “critical”, and “blocker”.
    @Owner("My Ngo")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AT-103")
    @TmsLink("AT-TR-53")
    @Parameters({"CardName", "CardNumber"}) //Global parameters for every test inside block <test> in testng-parameters-allure.xml
    public void verifyValidationMessageOnCardCVCField(String cardName, String cardNumber) {
//        PaymentModel missingCardNumberForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);
//        missingCardNumberForm.getCard().setCardNumber("");


        CardModel cardModel = new CardModel();
        cardModel.setCardName(cardName);
        cardModel.setCardNumber(cardNumber);
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setCard(cardModel);

        pages().paymentPage.goToPaymentPage();
        pages().paymentPage.fillPaymentFormWithBlankField(paymentModel)
                .submitPayment();
        Allure.step("Verify display 'Please fill out this field.' message when submitting", () -> {
            Assert.assertEquals(pages().paymentPage.cardDetailsForm.getCardCVCValidationMessage(), "Please fill out this field.");
        });
    }




}
