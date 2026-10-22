
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationEmailTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the registration email field accepts a valid email.
    @Test
    public void verifyEmailFieldAcceptsInput() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        emailField.sendKeys("customer.test@example.com");

        Assert.assertEquals(
            emailField.getAttribute("value"),
            "customer.test@example.com",
            "Email field did not accept the entered value"
        );
    }

    // Verify that the registration email field can be cleared.
    @Test
    public void verifyEmailFieldCanBeCleared() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        emailField.sendKeys("customer.test@example.com");
        emailField.clear();

        Assert.assertEquals(
            emailField.getAttribute("value"),
            "",
            "Email field was not cleared"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
