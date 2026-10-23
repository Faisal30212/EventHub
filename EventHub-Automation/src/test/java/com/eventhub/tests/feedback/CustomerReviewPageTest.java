
package com.eventhub.tests.feedback;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerReviewPageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String REVIEW_URL =
            "https://event-hub-eta-six.vercel.app/reviews";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        driver.get(REVIEW_URL);

        wait.until(d ->
                d.findElement(By.tagName("body")).isDisplayed());
    }

    @Test
    public void verifyReviewRouteOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/reviews"),
                "Reviews route did not open.");
    }

    @Test
    public void verifyReviewPageHasVisibleContent() {
        String pageText = driver.findElement(
                By.tagName("body")).getText().trim();

        Assert.assertFalse(
                pageText.isEmpty(),
                "Reviews page contains no visible content.");

        Assert.assertFalse(
                pageText.contains("Page Not Found"),
                "Reviews route displayed the not-found page.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
