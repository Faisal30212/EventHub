
package com.eventhub.tests.venues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerVenueBrowsingTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/venues");
    }

    // Verify that the venue listing page opens successfully.
    @Test
    public void verifyVenueListingPageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub website did not load"
        );
    }

    // Verify that the venue listing page displays content.
    @Test
    public void verifyVenueListingContent() {
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertTrue(
            body.isDisplayed() && !body.getText().trim().isEmpty(),
            "Venue listing page contains no visible text"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
