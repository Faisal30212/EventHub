
package com.eventhub.tests.events;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventBrowsingTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the home page displays event-related links or cards.
    @Test
    public void verifyEventContentIsDisplayed() {
        List<WebElement> eventElements = driver.findElements(
                By.cssSelector(".event-card, .featured-event, a[href*='event']")
        );

        Assert.assertTrue(
                eventElements.size() > 0,
                "No event-related content was found on the home page"
        );
    }

    // Verify that event-related links have valid non-empty destinations.
    @Test
    public void verifyEventLinksHaveDestinations() {
        List<WebElement> eventLinks = driver.findElements(
                By.cssSelector("a[href*='event']")
        );

        Assert.assertTrue(
                eventLinks.size() > 0,
                "No event links were found"
        );

        for (WebElement link : eventLinks) {
            Assert.assertFalse(
                    link.getDomAttribute("href") == null
                    || link.getDomAttribute("href").isBlank(),
                    "An event link has no destination"
            );
        }
    }

    // Verify that the home page contains visible content.
    @Test
    public void verifyHomePageBodyIsVisible() {
        Assert.assertTrue(
                driver.findElement(By.tagName("body")).isDisplayed(),
                "Home page body is not visible"
        );

        Assert.assertFalse(
                driver.findElement(By.tagName("body")).getText().isBlank(),
                "Home page has no visible text"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
