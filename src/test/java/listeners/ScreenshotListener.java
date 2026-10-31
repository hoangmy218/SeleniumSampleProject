package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.AttachmentOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static driver.DriverFactory.getDriver;

public class ScreenshotListener extends TestListenerAdapter {

    private boolean createFile(File screenshot) throws IOException {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                fileCreated = screenshot.createNewFile();
            }
        }
        return fileCreated;
    }

    private byte[] makeScreenshotOnFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void writeScreenshotToFile(WebDriver driver, File screenshot, byte[] screenshotBytes) throws IOException {
        FileOutputStream screenshotStream = new FileOutputStream(screenshot);
        screenshotStream.write(screenshotBytes);
        screenshotStream.close();
    }

    @Override
    @Attachment(type = "image/png")
    public void onTestFailure(ITestResult failingTest) {
        try {
            WebDriver driver = getDriver();

            String screenshotDirectory = System.getProperty("screenshotDirectory");
            String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis()
                    + "_" + failingTest.getName() + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                //Capture screenshot bytes
                byte[] screenshotBytes = makeScreenshotOnFailure(driver);
                try {
                    //ATTACH TO ALLURE REPORT (This is what was missing)
                    Allure.attachment(
                            "Screenshot on Failure - " + failingTest.getName(),
                            "image/png",
                            new ByteArrayInputStream(screenshotBytes),
                            AttachmentOptions.withFileExtension(".png")
                    );
                    writeScreenshotToFile(driver, screenshot, screenshotBytes);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenshotToFile(new Augmenter().augment(driver), screenshot, screenshotBytes);
                }
                System.out.println("Written screenshot to " + screenshotAbsolutePath);
                System.out.println("Test Failed at page: " + driver.getCurrentUrl());
            } else {
                System.out.println("Unable to create " + screenshotAbsolutePath);
            }
        } catch (Exception ex) {
            System.out.println("Unable to capture screenshot...");
            ex.printStackTrace();
        }
    }
}
