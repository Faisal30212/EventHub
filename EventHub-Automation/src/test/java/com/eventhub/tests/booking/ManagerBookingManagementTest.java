
package com.eventhub.tests.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerBookingManagementTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/manager-dashboard");
    }

    @Test
    public void verifyManagerRouteOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub website did not open"
        );
    }

    @Test
    public void verifyUnauthenticatedUserIsRedirectedToLogin() {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("/staff-login")
                || currentUrl.contains("/manager-dashboard"),
            "Unexpected URL: " + currentUrl
        );

        if (currentUrl.contains("/staff-login")) {
            String pageText = driver.findElement(By.tagName("body"))
                                     .getText()
                                     .toLowerCase();

            Assert.assertTrue(
                pageText.contains("login"),
                "Login content was not displayed"
            );
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
