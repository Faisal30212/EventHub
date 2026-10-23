
package com.eventhub.tests.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerInvalidLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(30));

        driver.get(BASE_URL + "/staff-login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']")));
    }

    @Test
    public void verifyInvalidCredentialsDoNotOpenDashboard() {

        driver.findElement(By.cssSelector("input[type='email']"))
                .sendKeys("invalid-test@example.com");

        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys("WrongPassword123!");

        WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit']"));

        submitButton.click();

        // Allow the login request and React state update to finish.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Test interrupted while waiting for login response.");
        }

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Invalid credentials should not navigate away from staff login."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
