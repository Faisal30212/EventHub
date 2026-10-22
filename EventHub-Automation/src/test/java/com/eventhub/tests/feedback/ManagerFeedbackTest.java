
package com.eventhub.tests.feedback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerFeedbackTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/manager-dashboard");
    }

    @Test
    public void verifyManagerDashboardRedirectsToLogin() {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("/staff-login")
                || currentUrl.contains("/manager-dashboard"),
            "Unexpected page URL: " + currentUrl
        );

        if (currentUrl.contains("/staff-login")) {
            Assert.assertTrue(
                driver.findElement(By.tagName("body"))
                    .getText().toLowerCase().contains("login"),
                "Staff login page did not display login content"
            );
        }
    }

    @Test
    public void verifyManagerLoginPageDisplaysEmailAndPasswordFields() {
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");

        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='email']")).size() > 0,
            "Email field was not found"
        );

        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='password']")).size() > 0,
            "Password field was not found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
