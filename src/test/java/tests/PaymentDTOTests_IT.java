package tests;

import driver.JSONDataLoader;
import io.qameta.allure.Allure;
import model.PaymentModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PaymentDTOTests_IT extends BaseTest{

    @DataProvider(name = "paymentFormData")
    public Object[][] getPaymentFormData() {
        // Reads JSON files from src/test/resources/testdata/

        PaymentModel validPaymentForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);

        PaymentModel missingCardNameForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);
        missingCardNameForm.getCard().setCardName("");

        PaymentModel invalidCardNumberForm = JSONDataLoader.loadData("testdata/valid_payment.json", PaymentModel.class);
        invalidCardNumberForm.getCard().setCardNumber("invalid card number 123");

        return new Object[][] {
                { "TC_PM_01: Valid Card Payment Submission", validPaymentForm },
                { "TC_PM_02: Error on missing Card Name", missingCardNameForm},
                { "TC_PM_03: Error on invalid Card Number", invalidCardNumberForm}
        };
    }

    @Test(dataProvider = "paymentFormData")
    public void placeOrderSuccess(String testCaseId, PaymentModel paymentData) {

        // Update Allure Report title dynamically with the ID
        Allure.getLifecycle().updateTestMetadata(test -> test.setDescription(testCaseId));

        pages().paymentPage.goToPaymentPage();
        pages().paymentPage.fillPaymentForm(paymentData)
                .submitPayment();
        Allure.step("Verify display 'Order Placed!' Text when payment done", () -> {
            Assert.assertTrue(pages().paymentPage.isPaymentDone(), "User order placed successfully!");
        });
    }
}
