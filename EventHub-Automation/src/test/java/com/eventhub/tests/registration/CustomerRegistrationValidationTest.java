
package com.eventhub.tests.registration;

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

public class CustomerRegistrationValidationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String REGISTER_URL =
            "https://event-hub-eta-six.vercel.app/register";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get(REGISTER_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")));
    }

    @Test
    public void verifyRegistrationPageOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/register"),
                "Customer registration page did not open.");

        Assert.assertFalse(
                driver.findElement(By.tagName("body"))
                      .getText().trim().isEmpty(),
                "Registration page contains no visible text.");
    }

    @Test
    public void verifyRegistrationFormHasRequiredFields() {
        Assert.assertTrue(
                driver.findElements(
                        By.cssSelector("input[type='email']")).size() > 0,
                "Registration email field was not found.");

        Assert.assertTrue(
                driver.findElements(
                        By.cssSelector("input[type='password']")).size() > 0,
                "Registration password field was not found.");

        Assert.assertTrue(
                driver.findElements(By.tagName("button")).stream()
                        .anyMatch(WebElement::isDisplayed),
                "No visible registration button was found.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
