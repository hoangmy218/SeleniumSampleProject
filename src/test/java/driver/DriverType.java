package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Collections;
import java.util.HashMap;

public enum DriverType implements DriverSetup {
    FIREFOX {
        @Override
        public WebDriver getWebDriverObject() {
            FirefoxOptions options = new FirefoxOptions();
            String headless = System.getProperty("headless");
            if (headless!=null && headless.equalsIgnoreCase("yes")) {
                //Set headless mode
                options.addArguments("--headless");
            }
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            firefoxProfile.setPreference("browser.download.dir", System.getProperty("downloadFilePath"));
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile","text/csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
            firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
            firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
            firefoxProfile.setPreference("browser.download.manager.useWindow", false);
            firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
            firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
            // Automatically setup the Firefox driver
            WebDriverManager.firefoxdriver().setup();

            // return the WebDriver instance
            return new FirefoxDriver(options);
        }
    },
    CHROME {
        @Override
        public WebDriver getWebDriverObject() {
            ChromeOptions options = new ChromeOptions();
            ApplicationProperties appInfo = DriverFactory.getApplicationInfo();
            String headless = System.getProperty("headless");
            //Set Window size
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            if (headless!=null && headless.equalsIgnoreCase("yes")) {
                //Set headless mode
                options.addArguments("--headless=new");
            }

            //Hide "make Chrome your default browser" pop-up
            options.addArguments("--no-default-browser-check");
            //Disable extension
            options.addArguments("chrome.switches", "--disable-extensions");
            //Disable install other chrome extensions
            options.setExperimentalOption("useAutomationExtension", false);
            //hide automation traces ("Chrome is being controlled by automated test software" message)
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            //for handling insecure downloads
            options.addArguments("--allow-running-insecure-content");
//            options.addArguments("--unsafely-treat-insecure-origin-as-secure=" + DriverFactory.credentials.get("url"));
//            options.addArguments("--unsafely-treat-insecure-origin-as-secure=" + appInfo.url);

            //Chrome Preferences
            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            //Set default download folder
            chromePreferences.put("download.default_directory", System.getProperty("downloadFilePath"));
            // Ensures the directory is updated
            chromePreferences.put("download.directory_upgrade", true);
            // for handling insecure downloads
            //Block pop-up ("Save as" dialog)
            chromePreferences.put("profile.default_content_settings.popups", 0);
            // Disable the "Ask where to save each file before downloading" prompt
            chromePreferences.put("download.prompt_for_download", false);
            //Disable safe browsing warnings for potentially harmful files
            chromePreferences.put("safebrowsing.enabled", "false");
            //Disable password manager
            chromePreferences.put("credentials_enable_service", false);
            chromePreferences.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", chromePreferences);
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }
    },
    EDGE {
        @Override
        public WebDriver getWebDriverObject() {
            EdgeOptions options = new EdgeOptions();
            String headless = System.getProperty("headless");
            if (headless!=null && headless.equalsIgnoreCase("yes")) {
                //Set headless mode
                options.addArguments("--headless=new");
            }
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(options);
        }
    },
    SAFARI {
        @Override
        public WebDriver getWebDriverObject() {
            SafariOptions options = new SafariOptions();
            WebDriverManager.safaridriver().setup();
            return new SafariDriver(options);
        }
    }
}

