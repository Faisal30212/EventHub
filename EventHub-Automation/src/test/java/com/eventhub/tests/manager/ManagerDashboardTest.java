
package com.eventhub.tests.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerDashboardTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(
                "https://event-hub-eta-six.vercel.app/manager-dashboard");
    }

    // Verify that the manager dashboard URL opens on EventHub.
    @Test
    public void verifyManagerDashboardUrl() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
                "EventHub website did not open");
    }

    // Verify that the manager dashboard displays visible content.
    @Test
    public void verifyManagerDashboardContent() {
        WebElement body = driver.findElement(By.tagName("body"));

        Assert.assertTrue(body.isDisplayed());

        Assert.assertFalse(
                body.getText().trim().isEmpty(),
                "Manager dashboard contains no visible text");
    }

    // Verify that the dashboard does not show a browser connection error.
    @Test
    public void verifyNoBrowserConnectionError() {
        Assert.assertFalse(
                driver.getPageSource().contains("This site can't be reached"),
                "Browser displayed a connection error");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
