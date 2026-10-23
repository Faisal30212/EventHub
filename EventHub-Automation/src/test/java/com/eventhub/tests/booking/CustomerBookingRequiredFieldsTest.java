
package com.eventhub.tests.booking;

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

public class CustomerBookingRequiredFieldsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(60));

        driver.get(BASE_URL + "/bookings");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("form.booking-card")));
    }

    @Test
    public void verifyBookingFormContainsRequiredFields() {
        WebElement form = driver.findElement(
                By.cssSelector("form.booking-card"));

        Assert.assertTrue(
                form.findElements(
                        By.cssSelector("input[type='date']")).size() > 0,
                "Event date field is missing.");

        Assert.assertTrue(
                form.findElements(
                        By.cssSelector("input[type='number']")).size() > 0,
                "Guest count field is missing.");

        Assert.assertTrue(
                form.findElements(
                        By.cssSelector("textarea")).size() > 0,
                "Requirements field is missing.");

        Assert.assertTrue(
                form.findElements(
                        By.cssSelector(".submit-booking-button")).size() > 0,
                "Booking submit button is missing.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
