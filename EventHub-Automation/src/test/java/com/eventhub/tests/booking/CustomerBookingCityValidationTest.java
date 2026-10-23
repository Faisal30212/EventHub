
package com.eventhub.tests.booking;

import java.time.Duration;
import java.util.List;

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

public class CustomerBookingCityValidationTest {

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

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear();"
        );

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem(" +
                "'user', JSON.stringify({userId: 999999}));"
        );

        driver.get(BASE_URL + "/bookings");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".booking-loading")
        ));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("form.booking-card")
        ));
    }

    @Test
    public void verifyCityIsRequired() {

        List<WebElement> eventCards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".selection-card")
                )
        );

        WebElement availableEvent = null;

        for (WebElement card : eventCards) {
            WebElement description = card.findElement(
                    By.tagName("small"));

            if (!description.getText().trim()
                    .equals("Event Management")) {
                availableEvent = card;
                break;
            }
        }

        Assert.assertNotNull(
                availableEvent,
                "No available event package was displayed."
        );

        availableEvent.click();

        WebElement bookingForm = driver.findElement(
                By.cssSelector("form.booking-card"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(" +
                "new Event('submit', " +
                "{bubbles: true, cancelable: true}));",
                bookingForm
        );

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".booking-error"))
        );

        Assert.assertEquals(
                errorMessage.getText().trim(),
                "Please select a city.",
                "City-required validation did not match."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
