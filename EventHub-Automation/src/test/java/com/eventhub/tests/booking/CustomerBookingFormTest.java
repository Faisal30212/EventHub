
package com.eventhub.tests.booking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerBookingFormTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BOOKING_URL =
            "https://event-hub-eta-six.vercel.app/bookings";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(25));

        driver.get(BOOKING_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".booking-header h1")));
    }

    @Test
    public void verifyBookingFormOpens() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/bookings"),
                "Customer booking page did not open.");

        Assert.assertEquals(
                driver.findElement(
                        By.cssSelector(".booking-header h1"))
                     .getText().trim(),
                "Plan Your Event 🎉",
                "Unexpected booking page heading.");
    }

    @Test
    public void verifyEventTypesAndCitiesAreDisplayed() {
        Assert.assertEquals(
                driver.findElements(
                        By.cssSelector(".selection-card")).size(),
                6,
                "Expected six event type options.");

        Assert.assertEquals(
                driver.findElements(
                        By.cssSelector(".city-card")).size(),
                4,
                "Expected four city options.");
    }

    @Test
    public void verifyBookingFormFieldsAreDisplayed() {
        Assert.assertEquals(
                driver.findElements(
                        By.cssSelector("input[type='date']")).size(),
                1,
                "Event date field is missing.");

        Assert.assertEquals(
                driver.findElements(
                        By.cssSelector("input[type='time']")).size(),
                1,
                "Event time field is missing.");

        Assert.assertEquals(
                driver.findElements(
                        By.cssSelector("input[type='number']")).size(),
                1,
                "Guest count field is missing.");

        Assert.assertEquals(
                driver.findElements(By.cssSelector("textarea")).size(),
                1,
                "Additional requirements field is missing.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector(".submit-booking-button"))
                     .isDisplayed(),
                "Submit Event Request button is not visible.");
    }

    @Test
    public void verifyBookingRequiresCustomerLogin() {
        WebElement bookingForm = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("form.booking-card")));

        // Dispatch the form submit event without relying on the
        // obstructed button's screen coordinates.
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(" +
                "new Event('submit', {" +
                "bubbles: true, cancelable: true}));",
                bookingForm);

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".booking-error")));

        Assert.assertEquals(
                errorMessage.getText().trim(),
                "Please login before submitting an event request.",
                "Expected the form to require customer login.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
