
package com.eventhub.tests.booking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerBookingAccessTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String HOME_URL =
            "https://event-hub-eta-six.vercel.app/";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get(HOME_URL);
    }

    @Test
    public void verifyHomepageOpens() {
        wait.until(d ->
                d.findElement(By.tagName("body")).isDisplayed());

        Assert.assertTrue(
                driver.getCurrentUrl().startsWith(HOME_URL),
                "EventHub homepage did not open.");

        Assert.assertEquals(
                driver.getTitle(),
                "EventHub",
                "Unexpected homepage title.");
    }

    @Test
    public void verifyEventContentIsDisplayed() {
        WebElement body = wait.until(
                d -> d.findElement(By.tagName("body")));

        Assert.assertTrue(
                body.isDisplayed(),
                "Homepage body is not visible.");

        String pageText = body.getText().trim();

        Assert.assertFalse(
                pageText.isEmpty(),
                "Homepage contains no visible text.");

        Assert.assertTrue(
                pageText.toLowerCase().contains("event"),
                "Homepage does not contain expected event-related text.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
