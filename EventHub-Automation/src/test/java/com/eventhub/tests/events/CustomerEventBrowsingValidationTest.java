
package com.eventhub.tests.events;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerEventBrowsingValidationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        driver.get(BASE_URL + "/events");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")));
    }

    @Test
    public void verifyEventsPageOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/events"),
                "Events page did not open.");

        Assert.assertFalse(
                driver.findElement(By.tagName("body"))
                      .getText().trim().isEmpty(),
                "Events page has no visible text.");
    }

    @Test
    public void verifyEventCategoriesAreDisplayed() {
        String pageText = driver.findElement(By.tagName("body"))
                                .getText()
                                .toLowerCase();

        Assert.assertTrue(
                pageText.contains("wedding")
                        || pageText.contains("birthday")
                        || pageText.contains("conference")
                        || pageText.contains("concert")
                        || pageText.contains("sports"),
                "No expected event categories were found.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
