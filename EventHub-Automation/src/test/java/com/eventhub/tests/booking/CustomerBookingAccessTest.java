
package com.eventhub.tests.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerBookingAccessTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the EventHub homepage opens successfully.
    @Test
    public void verifyHomepageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not open"
        );
    }

    // Verify that the homepage displays event-related content.
    @Test
    public void verifyEventContentIsDisplayed() {
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertTrue(
            body.isDisplayed() && !body.getText().trim().isEmpty(),
            "Homepage does not display event-related content"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
