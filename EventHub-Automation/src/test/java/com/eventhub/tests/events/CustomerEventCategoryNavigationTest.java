
package com.eventhub.tests.events;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerEventCategoryNavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the homepage displays event category content.
    @Test
    public void verifyEventCategoriesDisplayed() {
        WebElement body = driver.findElement(By.tagName("body"));
        String pageText = body.getText().toLowerCase();

        Assert.assertTrue(
            pageText.contains("wedding")
                || pageText.contains("birthday")
                || pageText.contains("conference"),
            "Event category content was not found on the homepage"
        );
    }

    // Verify that the homepage remains accessible after loading category content.
    @Test
    public void verifyHomepageRemainsAccessible() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage is not accessible"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
