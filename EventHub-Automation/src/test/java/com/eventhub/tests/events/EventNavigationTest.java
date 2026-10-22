
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

public class EventNavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the home page contains at least one internal navigation link.
    @Test
    public void verifyInternalNavigationLinks() {
        List<WebElement> links = driver.findElements(
                By.cssSelector("a[href^='/']")
        );

        Assert.assertTrue(
                links.size() > 0,
                "No internal navigation links were found"
        );
    }

    // Verify that an internal link navigates to a different page when clicked.
    @Test
    public void verifyInternalLinkNavigation() {
        List<WebElement> links = driver.findElements(
                By.cssSelector("a[href^='/']")
        );

        WebElement targetLink = null;

        for (WebElement link : links) {
            String href = link.getDomAttribute("href");

            if (href != null && !href.equals("/")) {
                targetLink = link;
                break;
            }
        }

        Assert.assertNotNull(
                targetLink,
                "No suitable internal navigation link was found"
        );

        String originalUrl = driver.getCurrentUrl();
        targetLink.click();

        Assert.assertNotEquals(
                driver.getCurrentUrl(),
                originalUrl,
                "Clicking the internal link did not change the URL"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
