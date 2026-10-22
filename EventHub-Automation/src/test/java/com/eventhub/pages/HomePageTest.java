
package com.eventhub.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the EventHub home page opens successfully.
    @Test
    public void verifyHomePageUrl() {
        Assert.assertTrue(
                driver.getCurrentUrl().equals("https://event-hub-eta-six.vercel.app/"),
                "EventHub home page did not open correctly"
        );
    }

    // Verify that the home page has a non-empty title.
    @Test
    public void verifyHomePageTitle() {
        String actualTitle = driver.getTitle();

        Assert.assertFalse(
                actualTitle.isEmpty(),
                "Home page title should not be empty"
        );

        System.out.println("Home Page Title: " + actualTitle);
    }

    // Verify that the home page loads without a browser error page.
    @Test
    public void verifyHomePageDoesNotShowBrowserError() {
        String pageSource = driver.getPageSource();

        Assert.assertFalse(
                pageSource.contains("This site can't be reached"),
                "Browser displayed a connection error"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
