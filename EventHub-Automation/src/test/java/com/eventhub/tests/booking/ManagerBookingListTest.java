
package com.eventhub.tests.booking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerBookingListTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(30));

        driver.get(BASE_URL + "/staff-login");
    }

    @Test
    public void verifyAuthenticatedManagerCanAccessBookingList() {

        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email,
                "EVENTHUB_MANAGER_EMAIL environment variable is missing.");

        Assert.assertNotNull(password,
                "EVENTHUB_MANAGER_PASSWORD environment variable is missing.");

        driver.findElement(
                By.cssSelector("input[type='email']"))
                .sendKeys(email);

        driver.findElement(
                By.cssSelector("input[type='password']"))
                .sendKeys(password);

        driver.findElement(
                By.cssSelector("button.login-btn"))
                .click();

        wait.until(ExpectedConditions.urlContains(
                "/manager-dashboard"));

        wait.until(d ->
                d.findElements(By.cssSelector(".manager-table")).size() > 0
                || d.findElements(By.cssSelector(".manager-empty"))
                       .stream()
                       .anyMatch(e -> e.getText().contains(
                               "No event requests found"))
                || d.findElements(By.cssSelector(
                        ".manager-message-error")).size() > 0);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/manager-dashboard"),
                "Manager did not reach the dashboard.");

        Assert.assertEquals(
                driver.findElements(By.cssSelector(
                        ".manager-message-error")).size(),
                0,
                "Manager dashboard could not load booking data.");

        boolean tableVisible = driver.findElements(
                By.cssSelector(".manager-table")).size() > 0;

        boolean emptyStateVisible = driver.findElements(
                By.cssSelector(".manager-empty"))
                .stream()
                .anyMatch(e -> e.getText().contains(
                        "No event requests found"));

        Assert.assertTrue(
                tableVisible || emptyStateVisible,
                "Neither the booking table nor the empty state appeared.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
