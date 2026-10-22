
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

public class EventCategoryTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the home page displays event category links.
    @Test
    public void verifyEventCategoryLinks() {
        List<WebElement> links = driver.findElements(
                By.cssSelector("a[href*='wedding'], a[href*='birthday'], "
                        + "a[href*='conference'], a[href*='concert'], "
                        + "a[href*='sports'], a[href*='college']")
        );

        Assert.assertTrue(
                links.size() > 0,
                "No event category links were found"
        );
    }

    // Verify that event category links have visible text.
    @Test
    public void verifyEventCategoryLinkText() {
        List<WebElement> links = driver.findElements(
                By.cssSelector("a[href*='wedding'], a[href*='birthday'], "
                        + "a[href*='conference'], a[href*='concert'], "
                        + "a[href*='sports'], a[href*='college']")
        );

        Assert.assertTrue(
                links.size() > 0,
                "No event category links were found"
        );

        for (WebElement link : links) {
            Assert.assertFalse(
                    link.getText().trim().isEmpty(),
                    "An event category link has no visible text"
            );
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
