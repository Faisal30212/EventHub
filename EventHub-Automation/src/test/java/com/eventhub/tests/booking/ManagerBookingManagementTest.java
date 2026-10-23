
package com.eventhub.tests.booking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerBookingManagementTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        // Start without a saved user session.
        driver.get(BASE_URL);

        driver.manage().deleteAllCookies();

        ((org.openqa.selenium.JavascriptExecutor) driver)
        .executeScript(
                "localStorage.clear(); sessionStorage.clear();");

        driver.get(BASE_URL + "/manager-dashboard");
    }

    @Test
    public void verifyUnauthenticatedUserIsRedirectedToStaffLogin() {
        wait.until(d ->
                d.getCurrentUrl().contains("/staff-login"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Unauthenticated user was not redirected to staff login.");
    }

    @Test
    public void verifyStaffLoginFormIsDisplayedAfterRedirect() {
        wait.until(d ->
                d.getCurrentUrl().contains("/staff-login"));

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='email']"))
                     .isDisplayed(),
                "Staff login email field is not visible.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='password']"))
                     .isDisplayed(),
                "Staff login password field is not visible.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
