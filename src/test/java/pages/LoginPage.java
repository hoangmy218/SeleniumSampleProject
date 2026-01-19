package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends BasePage {

    public static By emailAddressField = By.name("email");
    public static By passwordField = By.name("password");
    public static By loginBtn = By.xpath("//button[@data-qa='login-button']");

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public static void loginWithUsernameAndPassword(String email, String password, WebDriver driver) {
        driver.findElement(emailAddressField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();

    }

    public static void goToLoginPage(WebDriver driver) {
        driver.get("https://automationexercise.com/login");
        System.out.println("Print TEST " +
                Thread.currentThread().getId() + " -> " + driver
        );
    }
}
