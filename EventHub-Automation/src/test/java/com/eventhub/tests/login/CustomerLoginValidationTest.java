
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerLoginValidationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/login");
    }

    // Verify that the customer login email field rejects an invalid email format.
    @Test
    public void verifyInvalidEmailValidation() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        emailField.sendKeys("invalid-email");

        boolean emailIsValid = (Boolean)
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                    "return arguments[0].checkValidity();",
                    emailField
                );

        Assert.assertFalse(
            emailIsValid,
            "Invalid email passed browser validation"
        );
    }

    // Verify that the customer login password field masks entered characters.
    @Test
    public void verifyPasswordIsMasked() {
        WebElement passwordField =
            driver.findElement(By.cssSelector("input[type='password']"));

        passwordField.sendKeys("TestPassword123");

        Assert.assertEquals(
            passwordField.getAttribute("type"),
            "password",
            "Password field is not masked"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
