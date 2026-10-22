
package com.eventhub.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    @Test
    public void openGoogleAndVerifyTitle() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.com");

            System.out.println("Page title: " + driver.getTitle());

            Assert.assertTrue(
                driver.getTitle().contains("Google"),
                "Google page title was not found"
            );

            Thread.sleep(5000); // Keep Chrome open for 5 seconds

        } finally {
            driver.quit();
        }
    }
}
