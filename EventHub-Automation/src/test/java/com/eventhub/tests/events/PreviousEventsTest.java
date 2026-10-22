
package com.eventhub.tests.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PreviousEventsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    @Test
    public void verifyHomepageLoads() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not load"
        );
    }

    @Test
    public void verifyPreviousEventsContent() {
        String pageText = driver.findElement(By.tagName("body"))
                                 .getText()
                                 .toLowerCase();

        Assert.assertTrue(
            pageText.contains("previous events")
                || pageText.contains("past events")
                || pageText.contains("our events"),
            "Previous events content was not found on the homepage"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
