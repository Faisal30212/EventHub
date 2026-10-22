
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginPasswordVisibilityTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    // Verify that the staff login page contains a password field.
    @Test
    public void verifyPasswordFieldExists() {
        java.util.List<WebElement> passwordFields =
            driver.findElements(By.cssSelector("input[type='password']"));

        Assert.assertFalse(
            passwordFields.isEmpty(),
            "Password field was not found"
        );

        Assert.assertTrue(
            passwordFields.get(0).isDisplayed(),
            "Password field is not visible"
        );
    }

    // Verify that the password field masks entered characters.
    @Test
    public void verifyPasswordIsMasked() {
        WebElement passwordField =
            driver.findElement(By.cssSelector("input[type='password']"));

        passwordField.sendKeys("TestPassword123");

        Assert.assertEquals(
            passwordField.getAttribute("type"),
            "password",
            "Password field is not masking entered characters"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
