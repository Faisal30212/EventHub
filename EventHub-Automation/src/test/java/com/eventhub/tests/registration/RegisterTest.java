
package com.eventhub.tests.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest {

    // Verify that the EventHub registration page opens successfully.
    @Test
    public void verifyRegistrationPageOpens() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(
                "https://event-hub-eta-six.vercel.app/register"
            );

            Assert.assertTrue(
                driver.getCurrentUrl().contains("/register"),
                "Registration page did not open"
            );

            System.out.println(
                "Registration URL: " + driver.getCurrentUrl()
            );

        } finally {
            driver.quit();
        }
    }
}
