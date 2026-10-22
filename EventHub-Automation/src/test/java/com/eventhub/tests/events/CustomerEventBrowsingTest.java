
package com.eventhub.tests.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerEventBrowsingTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the homepage displays event information.
    @Test
    public void verifyEventInformationDisplayed() {
        String pageText = driver.findElement(By.tagName("body"))
                                .getText()
                                .toLowerCase();

        Assert.assertTrue(
            pageText.contains("event")
                || pageText.contains("wedding")
                || pageText.contains("conference"),
            "Event information was not displayed on the homepage"
        );
    }

    // Verify that the homepage contains images or links for browsing events.
    @Test
    public void verifyEventBrowsingElements() {
        java.util.List<WebElement> images =
            driver.findElements(By.cssSelector("img"));

        java.util.List<WebElement> links =
            driver.findElements(By.cssSelector("a"));

        Assert.assertTrue(
            !images.isEmpty() || !links.isEmpty(),
            "No event browsing images or links were found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
