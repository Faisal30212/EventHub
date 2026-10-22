
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

public class BookingFormTest {

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

    // Verify that the EventHub home page opens before starting booking.
    @Test
    public void verifyHomePageBeforeBooking() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
                "EventHub home page did not open");
    }

    // Verify that the home page contains a visible booking-related action.
    @Test
    public void verifyBookingActionIsVisible() {
        java.util.List<WebElement> actions = driver.findElements(
                By.cssSelector(
                        "a[href*='book'], a[href*='booking'], "
                        + "button[class*='book']"));

        boolean visibleActionFound = false;

        for (WebElement action : actions) {
            if (action.isDisplayed()) {
                visibleActionFound = true;
                break;
            }
        }

        Assert.assertTrue(
                visibleActionFound,
                "No visible booking action was found");
    }

    // Verify that the page has loaded its main content.
    @Test
    public void verifyPageContentIsDisplayed() {
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertTrue(body.isDisplayed());
        Assert.assertFalse(
                body.getText().trim().isEmpty(),
                "The page has no visible content");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
