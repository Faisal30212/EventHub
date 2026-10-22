
package com.eventhub.tests.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerMyEventsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/my-events");
    }

    // Verify that the My Events route loads within EventHub.
    @Test
    public void verifyMyEventsRouteLoads() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub website did not load"
        );
    }

    // Verify that the My Events page displays content.
    @Test
    public void verifyMyEventsPageContent() {
        String pageText = driver.findElement(By.tagName("body")).getText();

        Assert.assertFalse(
            pageText.trim().isEmpty(),
            "My Events page contains no visible text"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
