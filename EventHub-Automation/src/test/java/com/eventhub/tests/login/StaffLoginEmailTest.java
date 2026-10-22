
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginEmailTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    // Verify that the staff login page contains an email field.
    @Test
    public void verifyEmailFieldExists() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        Assert.assertTrue(
            emailField.isDisplayed(),
            "Email field is not displayed"
        );
    }

    // Verify that the email field accepts text input.
    @Test
    public void verifyEmailFieldAcceptsText() {
        WebElement emailField =
            driver.findElement(By.cssSelector("input[type='email']"));

        emailField.sendKeys("test@example.com");

        Assert.assertEquals(
            emailField.getAttribute("value"),
            "test@example.com",
            "Email field did not accept the entered text"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
