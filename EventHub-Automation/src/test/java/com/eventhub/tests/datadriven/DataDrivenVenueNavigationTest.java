
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenVenueNavigationTest {

    private WebDriver driver;

    @DataProvider(name = "venueRoutes")
    public Object[][] venueRoutes() {
        return new Object[][] {
            {"Homepage", "https://event-hub-eta-six.vercel.app/"},
            {"Venues", "https://event-hub-eta-six.vercel.app/venues"}
        };
    }

    @Test(dataProvider = "venueRoutes")
    public void verifyVenueRouteLoads(String pageName, String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Assert.assertTrue(
            driver.getCurrentUrl().startsWith("https://event-hub-eta-six.vercel.app"),
            pageName + " did not open on EventHub"
        );

        Assert.assertFalse(
            driver.getTitle().trim().isEmpty(),
            pageName + " page title is empty"
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
