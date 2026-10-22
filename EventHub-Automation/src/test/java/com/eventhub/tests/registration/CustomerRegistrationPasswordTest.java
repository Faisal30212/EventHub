
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationPasswordTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the registration password field accepts input.
    @Test
    public void verifyPasswordFieldAcceptsInput() {
        WebElement passwordField =
            driver.findElement(By.cssSelector("input[type='password']"));

        passwordField.sendKeys("TestPassword123");

        Assert.assertEquals(
            passwordField.getAttribute("value"),
            "TestPassword123",
            "Password field did not accept the entered value"
        );
    }

    // Verify that the registration password field can be cleared.
    @Test
    public void verifyPasswordFieldCanBeCleared() {
        WebElement passwordField =
            driver.findElement(By.cssSelector("input[type='password']"));

        passwordField.sendKeys("TestPassword123");
        passwordField.clear();

        Assert.assertEquals(
            passwordField.getAttribute("value"),
            "",
            "Password field was not cleared"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
