
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenBookingTest {

    private WebDriver driver;

    @DataProvider(name = "bookingInputs")
    public Object[][] bookingInputs() {
        return new Object[][] {
            {"Wedding"},
            {"Birthday"},
            {"Conference"},
            {"Concert"}
        };
    }

    @Test(dataProvider = "bookingInputs")
    public void verifyBookingEntryForEventTypes(String eventType) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not load for " + eventType
        );

        Assert.assertFalse(
            driver.getTitle().trim().isEmpty(),
            "Page title is empty for " + eventType
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

