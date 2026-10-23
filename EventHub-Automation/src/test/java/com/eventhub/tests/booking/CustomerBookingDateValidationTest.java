
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

public class CustomerBookingDateValidationTest {

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
                "window.localStorage.clear();");

        driver.get(BASE_URL + "/bookings");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("form.booking-card")));
    }

    @Test
    public void verifyDateInputAcceptsValidDate() {
        WebElement dateInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                "form.booking-card input[type='date']")));

        String today = (String) ((JavascriptExecutor) driver)
                .executeScript(
                        "return new Date().toISOString().split('T')[0];");

        ((JavascriptExecutor) driver).executeScript(
                "const input = arguments[0];" +
                "input.removeAttribute('min');" +
                "input.removeAttribute('max');" +
                "input.value = arguments[1];" +
                "input.dispatchEvent(new Event('input', {bubbles:true}));" +
                "input.dispatchEvent(new Event('change', {bubbles:true}));",
                dateInput, today);

        String selectedDate = dateInput.getAttribute("value");

        Assert.assertEquals(
                selectedDate,
                today,
                "The booking date input did not accept today's date.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
