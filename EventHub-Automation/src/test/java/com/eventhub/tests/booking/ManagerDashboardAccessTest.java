
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

public class ManagerDashboardAccessTest {

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
    }

    @Test
    public void verifyStaffLoginPageOpens() {
        driver.get(BASE_URL + "/staff-login");

        wait.until(d -> d.findElement(
                By.tagName("body")).getText().length() > 0);

        String pageText = driver.findElement(
                By.tagName("body")).getText().toLowerCase();

        Assert.assertTrue(
                pageText.contains("login")
                        || pageText.contains("password"),
                "Staff login page content was not displayed.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Browser did not remain on the staff login route.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
