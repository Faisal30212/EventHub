
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationFieldsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    
    // Verify that the registration form contains a name field.
    @Test
    public void verifyNameFieldExists() {
        java.util.List<WebElement> fields =
            driver.findElements(By.cssSelector(
                "input[name='name'], input[placeholder*='name' i], input[autocomplete='name']"
            ));

        Assert.assertFalse(
            fields.isEmpty(),
            "Name field was not found"
        );

        Assert.assertTrue(
            fields.get(0).isDisplayed(),
            "Name field is not displayed"
        );
    }

    // Verify that the registration form contains an email field.
    @Test
    public void verifyEmailFieldExists() {
        java.util.List<WebElement> fields =
            driver.findElements(By.cssSelector("input[type='email']"));

        Assert.assertFalse(fields.isEmpty(), "Email field was not found");
        Assert.assertTrue(fields.get(0).isDisplayed());
    }

    // Verify that the registration form contains a password field.
    @Test
    public void verifyPasswordFieldExists() {
        java.util.List<WebElement> fields =
            driver.findElements(By.cssSelector("input[type='password']"));

        Assert.assertFalse(fields.isEmpty(), "Password field was not found");
        Assert.assertTrue(fields.get(0).isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
