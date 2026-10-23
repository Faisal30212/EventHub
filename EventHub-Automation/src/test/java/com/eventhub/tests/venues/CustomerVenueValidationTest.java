
package com.eventhub.tests.venues;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerVenueValidationTest {

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

        driver.get(BASE_URL + "/venues");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")));
    }

    @Test
    public void verifyVenuePageOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/venues"),
                "Venue page did not open.");

        Assert.assertFalse(
                driver.findElement(By.tagName("body"))
                      .getText().trim().isEmpty(),
                "Venue page has no visible content.");
    }

    @Test
    public void verifyVenueInformationIsDisplayed() {
        String pageText = driver.findElement(By.tagName("body"))
                                .getText()
                                .toLowerCase();

        Assert.assertTrue(
                pageText.contains("venue")
                        || pageText.contains("location")
                        || pageText.contains("capacity"),
                "Expected venue-related information was not found.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
