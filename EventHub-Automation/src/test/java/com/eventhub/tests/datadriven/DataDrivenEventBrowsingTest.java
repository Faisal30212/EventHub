
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenEventBrowsingTest {

    private WebDriver driver;

    @DataProvider(name = "eventPages")
    public Object[][] eventPages() {
        return new Object[][] {
            {"Home", "https://event-hub-eta-six.vercel.app/"},
            {"Events", "https://event-hub-eta-six.vercel.app/"},
            {"Categories", "https://event-hub-eta-six.vercel.app/"}
        };
    }

    @Test(dataProvider = "eventPages")
    public void verifyEventHubPageLoads(String pageName, String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            pageName + " page did not load"
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
