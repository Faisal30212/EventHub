
package com.eventhub.tests.datadriven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenFeedbackTest {

    private WebDriver driver;

    @DataProvider(name = "feedbackPages")
    public Object[][] feedbackPages() {
        return new Object[][] {
            {"Homepage", "https://event-hub-eta-six.vercel.app/"},
            {"Customer Feedback Entry", "https://event-hub-eta-six.vercel.app/"}
        };
    }

    @Test(dataProvider = "feedbackPages")
    public void verifyFeedbackEntryPage(String pageName, String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            pageName + " did not load"
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
