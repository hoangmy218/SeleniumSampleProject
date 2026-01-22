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

    public void loginWithUsernameAndPassword(String email, String password, WebDriver driver) {
        System.out.println("Print TEST LOGIN DRIVER" +
                Thread.currentThread().getId() + " -> " + driver
        );
        driver.findElement(emailAddressField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();

    }

    public void loginWithUsernameAndPassword(String email, String password) {
        System.out.println(
                "[LoginPAGE] login Thread=" + Thread.currentThread().getId() +
                        " DriverHash=" + System.identityHashCode(driver)
        );
        driver.findElement(emailAddressField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public void goToLoginPage(WebDriver driver) {
        driver.get("https://automationexercise.com/login");
        System.out.println("Print TEST DRIVER" +
                Thread.currentThread().getId() + " -> " + driver
        );
    }

    public void goToLoginPage() {
        navigate("https://automationexercise.com/login");
//        driver.get("https://automationexercise.com/login");
        System.out.println(
                "[LoginPage] Nav Thread=" + Thread.currentThread().getId() +
                        " DriverHash=" + System.identityHashCode(driver)
        );
    }
}
