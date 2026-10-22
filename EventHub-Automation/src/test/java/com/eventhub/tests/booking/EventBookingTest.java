
package com.eventhub.tests.booking;

import java.time.Duration;
import java.util.List;

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

public class EventBookingTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the home page provides a way to start event booking.
    @Test
    public void verifyBookingNavigationIsAvailable() {
        List<WebElement> bookingLinks = driver.findElements(
                By.cssSelector(
                        "a[href*='booking'], a[href*='book'], "
                        + "button[class*='book']"));

        Assert.assertTrue(
                bookingLinks.size() > 0,
                "No booking navigation was found on the home page");
    }

    // Verify that clicking a booking link opens its destination.
    @Test
    public void verifyBookingNavigationWorks() {
        List<WebElement> bookingLinks = driver.findElements(
                By.cssSelector("a[href*='booking'], a[href*='book']"));

        Assert.assertTrue(
                bookingLinks.size() > 0,
                "No booking links were found");

        WebElement bookingLink = bookingLinks.get(0);
        String originalUrl = driver.getCurrentUrl();

        wait.until(
                ExpectedConditions.elementToBeClickable(bookingLink));

        bookingLink.click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlToBe(originalUrl)));

        Assert.assertNotEquals(
                driver.getCurrentUrl(),
                originalUrl,
                "Booking link did not navigate to another page");
    }

    // Verify that the opened booking page displays visible content.
    @Test
    public void verifyBookingPageContent() {
        List<WebElement> bookingLinks = driver.findElements(
                By.cssSelector("a[href*='booking'], a[href*='book']"));

        Assert.assertTrue(
                bookingLinks.size() > 0,
                "No booking links were found");

        bookingLinks.get(0).click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlToBe(
                                "https://event-hub-eta-six.vercel.app/")));

        WebElement body = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.tagName("body")));

        Assert.assertFalse(
                body.getText().trim().isEmpty(),
                "The destination page contains no visible text");
    }

    // Verify that the booking destination stays within the EventHub website.
    @Test
    public void verifyBookingDestinationIsInternal() {
        List<WebElement> bookingLinks = driver.findElements(
                By.cssSelector("a[href*='booking'], a[href*='book']"));

        Assert.assertTrue(
                bookingLinks.size() > 0,
                "No booking links were found");

        String destination = bookingLinks.get(0)
                .getDomAttribute("href");

        Assert.assertTrue(
                destination != null
                        && (destination.startsWith("/")
                        || destination.startsWith(
                                "https://event-hub-eta-six.vercel.app/")),
                "Booking link does not point to EventHub");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
