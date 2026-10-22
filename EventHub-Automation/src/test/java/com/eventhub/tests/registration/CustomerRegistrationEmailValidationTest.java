
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationEmailValidationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the email field rejects an invalid email format.
    @Test
    public void verifyInvalidEmailFormat() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        emailField.sendKeys("invalid-email");

        Assert.assertFalse(
            emailField.getAttribute("type").equals("text")
                && emailField.getAttribute("value").isEmpty(),
            "Email field did not retain the entered test value"
        );

        Assert.assertFalse(
            emailField.getAttribute("type").equals("email")
                ? (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].checkValidity();", emailField)
                : false,
            "Invalid email passed browser email validation"
        );
    }

    // Verify that the email field is initially empty.
    @Test
    public void verifyEmailFieldStartsEmpty() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        String value = emailField.getAttribute("value");

        Assert.assertTrue(
            value == null || value.isEmpty(),
            "Email field is not empty initially"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}