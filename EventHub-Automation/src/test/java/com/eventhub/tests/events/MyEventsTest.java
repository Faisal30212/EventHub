
package com.eventhub.tests.events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyEventsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/my-events");
    }

    // Verify that the My Events page opens on the EventHub website.
    @Test
    public void verifyMyEventsPageOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
                "EventHub website did not open");
    }

    // Verify that the My Events page displays visible content.
    @Test
    public void verifyMyEventsPageContent() {
        String pageText = driver.findElement(
                org.openqa.selenium.By.tagName("body")).getText();

        Assert.assertFalse(
                pageText.trim().isEmpty(),
                "My Events page contains no visible text");
    }

    // Verify that the My Events page does not display a browser connection error.
    @Test
    public void verifyNoBrowserConnectionError() {
        String pageSource = driver.getPageSource();

        Assert.assertFalse(
                pageSource.contains("This site can't be reached"),
                "The browser displayed a connection error");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
