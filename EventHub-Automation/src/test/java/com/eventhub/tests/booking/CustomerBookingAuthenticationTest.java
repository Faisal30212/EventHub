
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

public class CustomerBookingAuthenticationTest {

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

        driver.get(BASE_URL);

        driver.manage().deleteAllCookies();

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear();"
        );

        driver.get(BASE_URL + "/bookings");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("form.booking-card")
        ));
    }

    @Test
    public void verifyGuestCannotSubmitBookingRequest() {

        WebElement bookingForm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("form.booking-card")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(" +
                "new Event('submit', " +
                "{bubbles: true, cancelable: true}));",
                bookingForm
        );

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".booking-error")
                )
        );

        Assert.assertEquals(
                errorMessage.getText().trim(),
                "Please login before submitting an event request.",
                "Guest booking should require customer login."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}