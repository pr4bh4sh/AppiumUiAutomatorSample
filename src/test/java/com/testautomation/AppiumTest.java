package com.testautomation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class AppiumTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        String apkPath = Paths.get(System.getProperty("user.dir"), "apks", "BSF-IR.apk.zip").toString();

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setApp(apkPath)
                .setNoReset(false)
                .setFullReset(false)
                .autoGrantPermissions();


        // Initialize the AndroidDriver
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @Test
    public void testAppFunctionality() {
        tap(By.xpath("//android.widget.TextView[@text='Sign In / Sign Up']"));

        tap(By.xpath("//android.widget.TextView[@text='Continue with Google']"));
        // More test steps can be added here based on your application flow
        System.out.println("Test case executed successfully!");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Quit the driver after test execution
        }
    }

    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void tap(By by) {
        waitUntilElementVisible(by);
        driver.findElement(by).click();
    }
}
