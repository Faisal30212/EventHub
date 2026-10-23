
package com.eventhub.tests.booking;

import java.time.Duration;
import java.util.List;

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
    public void verifyHomePageBeforeBooking() {
        wait.until(d ->
                d.findElement(By.tagName("body")).isDisplayed());

        Assert.assertTrue(
                driver.getCurrentUrl().startsWith(HOME_URL),
                "EventHub home page did not open correctly.");

        Assert.assertTrue(
                driver.getTitle() != null
                        && !driver.getTitle().trim().isEmpty(),
                "The browser page title is empty.");

        System.out.println("Home page URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
    }

    @Test
    public void verifyBookingActionIsVisible() {
        By bookingActions = By.cssSelector(
                "a[href*='book'], "
                + "a[href*='booking'], "
                + "button[class*='book'], "
                + "a[class*='book'], "
                + "button[id*='book'], "
                + "a[id*='book']");

        List<WebElement> actions =
                driver.findElements(bookingActions);

        System.out.println(
                "Booking actions found: " + actions.size());

        boolean visibleActionFound = false;

        for (WebElement action : actions) {
            if (action.isDisplayed()) {
                visibleActionFound = true;

                System.out.println(
                        "Tag: " + action.getTagName()
                        + " | Text: " + action.getText()
                        + " | Href: "
                        + action.getAttribute("href"));
            }
        }

        Assert.assertTrue(
                visibleActionFound,
                "No visible booking action matched the configured selectors.");
    }

    @Test
    public void verifyPageContentIsDisplayed() {
        WebElement body = wait.until(
                d -> d.findElement(By.tagName("body")));

        Assert.assertTrue(
                body.isDisplayed(),
                "The page body is not visible.");

        Assert.assertFalse(
                body.getText().trim().isEmpty(),
                "The page has no visible text content.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
