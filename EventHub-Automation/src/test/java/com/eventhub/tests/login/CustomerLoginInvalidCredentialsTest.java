
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerLoginInvalidCredentialsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/login");
    }

    // Verify that invalid customer credentials do not grant access.
    @Test
    public void verifyInvalidCredentialsDoNotGrantAccess() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        WebElement passwordField =
            driver.findElement(By.cssSelector("input[type='password']"));

        emailField.sendKeys("invalid.user@example.com");
        passwordField.sendKeys("WrongPassword123");

        driver.findElements(By.tagName("button"))
            .stream()
            .filter(WebElement::isDisplayed)
            .filter(button ->
                button.getText().toLowerCase().contains("login"))
            .findFirst()
            .orElseThrow(() ->
                new AssertionError("Login button was not found"))
            .click();

        try {
            new org.openqa.selenium.support.ui.WebDriverWait(
                driver, java.time.Duration.ofSeconds(5))
                .until(d -> !d.getCurrentUrl().contains("/login"));
        } catch (org.openqa.selenium.TimeoutException e) {
            // The URL remained on the login page.
        }

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/login"),
            "Invalid credentials unexpectedly navigated away from login"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
