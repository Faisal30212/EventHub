
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenManagerTest {

    private WebDriver driver;

    @DataProvider(name = "managerPages")
    public Object[][] managerPages() {
        return new Object[][] {
            {"Manager Dashboard", "https://event-hub-eta-six.vercel.app/manager-dashboard"},
            {"Manager Login", "https://event-hub-eta-six.vercel.app/staff-login"}
        };
    }

    @Test(dataProvider = "managerPages")
    public void verifyManagerPages(String pageName, String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            pageName + " did not open on EventHub"
        );

        Assert.assertFalse(
            driver.getTitle().trim().isEmpty(),
            pageName + " has an empty page title"
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
