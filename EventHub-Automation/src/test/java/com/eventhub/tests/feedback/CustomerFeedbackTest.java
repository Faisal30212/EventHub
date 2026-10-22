
package com.eventhub.tests.feedback;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerFeedbackTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    @Test
    public void verifyFeedbackEntryPoint() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not load"
        );
    }

    @Test
    public void verifyFeedbackPageDoesNotAssumeHomepageForm() {
        Assert.assertTrue(
            driver.getCurrentUrl().endsWith("/")
                || driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "Unexpected page URL: " + driver.getCurrentUrl()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
