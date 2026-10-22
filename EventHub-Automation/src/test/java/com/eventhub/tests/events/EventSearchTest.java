
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

public class EventSearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the home page displays the Featured Events section.
    @Test
    public void verifyFeaturedEventsHeading() {
        WebElement heading = driver.findElement(
                By.cssSelector(".featured-heading h2"));

        Assert.assertEquals(heading.getText(), "Featured Events");
    }

    // Verify that the home page displays event cards.
    @Test
    public void verifyEventCardsAreDisplayed() {
        List<WebElement> eventCards = driver.findElements(
                By.cssSelector(".event-card"));

        Assert.assertTrue(
                eventCards.size() > 0,
                "No featured event cards were displayed");
    }

    // Verify that every featured event card has a Plan My Event link.
    @Test
    public void verifyPlanMyEventLinks() {
        List<WebElement> eventLinks = driver.findElements(
                By.cssSelector(".event-card a.event-btn"));

        Assert.assertTrue(
                eventLinks.size() > 0,
                "No Plan My Event links were found");

        for (WebElement link : eventLinks) {
            Assert.assertTrue(
                    link.isDisplayed(),
                    "A Plan My Event link is not visible");

            Assert.assertFalse(
                    link.getText().trim().isEmpty(),
                    "A Plan My Event link has no text");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
