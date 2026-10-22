
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenBookingFormTest {

    private WebDriver driver;

    @DataProvider(name = "bookingFormInputs")
    public Object[][] bookingFormInputs() {
        return new Object[][] {
            {"", ""},
            {"Faisal Test", "faisal.test@example.com"},
            {"Test User", "invalid-email"},
            {"Test User", ""}
        };
    }

    @Test(dataProvider = "bookingFormInputs")
    public void verifyBookingPageLoadsForInputData(
            String name, String email) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub did not load for: " + name
        );

        Assert.assertFalse(
            driver.getTitle().trim().isEmpty(),
            "EventHub page title is empty"
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
