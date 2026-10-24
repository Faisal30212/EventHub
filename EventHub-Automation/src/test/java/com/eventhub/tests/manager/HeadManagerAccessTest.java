
package com.eventhub.tests.manager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeadManagerAccessTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(BASE_URL + "/staff-login");
    }

    @Test
    public void verifyHeadManagerCanAccessAllRequests() {

        String email = System.getenv("EVENTHUB_HEAD_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_HEAD_MANAGER_PASSWORD");

        Assert.assertNotNull(email,
                "Head Manager email environment variable is missing.");

        Assert.assertNotNull(password,
                "Head Manager password environment variable is missing.");

        Assert.assertFalse(email.trim().isEmpty(),
                "Head Manager email cannot be empty.");

        Assert.assertFalse(password.isEmpty(),
                "Head Manager password cannot be empty.");

        // Enter Head Manager credentials.
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']")))
                .sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='password']")))
                .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.login-btn"))).click();

        // Wait until the dashboard opens.
        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")));

        String pageText =
                driver.findElement(By.tagName("body")).getText();

        System.out.println("HEAD MANAGER DASHBOARD CONTENT:");
        System.out.println(pageText);

        // Verify Head Manager identity.
        Assert.assertTrue(
                pageText.contains("Head Manager"),
                "Dashboard should identify the Head Manager.");

        // Verify the all-requests section.
        Assert.assertTrue(
                pageText.contains("All Event Requests"),
                "Dashboard should display the all-requests section.");

        // Confirm the dashboard remains accessible.
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/manager-dashboard"),
                "The browser should remain on the manager dashboard.");

        System.out.println(
                "PASS: Head Manager can access the dashboard and all-requests section.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
