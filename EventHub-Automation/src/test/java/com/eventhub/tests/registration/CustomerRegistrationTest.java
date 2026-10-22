
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the customer registration page opens successfully.
    @Test
    public void verifyRegistrationPageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/register"),
            "Registration page did not open"
        );
    }

    // Verify that the registration page displays visible content.
    @Test
    public void verifyRegistrationPageContent() {
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertTrue(
            body.isDisplayed() && !body.getText().trim().isEmpty(),
            "Registration page has no visible content"
        );
    }

    // Verify that the registration page contains input fields.
    @Test
    public void verifyRegistrationInputFields() {
        java.util.List<WebElement> inputs =
            driver.findElements(By.tagName("input"));

        Assert.assertTrue(
            inputs.size() >= 2,
            "Registration input fields were not found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
