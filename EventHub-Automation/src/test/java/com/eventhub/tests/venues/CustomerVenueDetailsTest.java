
package com.eventhub.tests.venues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerVenueDetailsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/venues/3");
    }

    // Verify that the venue details route opens within EventHub.
    @Test
    public void verifyVenueDetailsRouteOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub venue page did not load"
        );
    }

    // Verify that the venue details page displays information.
    @Test
    public void verifyVenueDetailsContent() {
        String pageText = driver.findElement(By.tagName("body")).getText();

        Assert.assertFalse(
            pageText.trim().isEmpty(),
            "Venue details page contains no visible text"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
