package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends BasePage {

//    public static By emailAddressField = By.name("email");
//    public static By passwordField = By.name("password");
//    public static By loginBtn = By.xpath("//button[@data-qa='login-button']");

    @FindBy(name = "email")
    private WebElement emailAddressField;

    @FindBy(name = "password")
    private  WebElement passwordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void loginWithUsernameAndPassword(String email, String password) {
        emailAddressField.sendKeys(email);
        passwordField.sendKeys(password);
        loginBtn.click();
    }

    public void goToLoginPage() {
        navigate("https://automationexercise.com/login");
    }
}
