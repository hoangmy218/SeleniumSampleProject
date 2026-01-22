package pages;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Pages {

    private static final ThreadLocal<Pages> PagesThread = new ThreadLocal<>();
    protected final WebDriver driver;
    public final LoginPage loginPage;
    public final LandingPage landingPage;

    public Pages(WebDriver driver) throws IOException {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.landingPage = new LandingPage(driver);
    }

    public static void init(WebDriver driver) throws IOException {
        PagesThread.set(new Pages(driver));
    }

    public static Pages get() {
        Pages pages = PagesThread.get();
        return pages;
    }

    public static void cleanup() {
        PagesThread.remove();
    }

}
