
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginPageTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    @Test
    public void verifyStaffLoginPageURL() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/staff-login"),
            "Staff login URL is incorrect"
        );
    }

    @Test
    public void verifyLoginHeading() {
        String pageText = driver.findElement(By.tagName("body"))
                                 .getText()
                                 .toLowerCase();

        Assert.assertTrue(
            pageText.contains("manager login"),
            "Manager Login heading was not displayed"
        );
    }

    @Test
    public void verifyEmailField() {
        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='email']")).size() > 0,
            "Email field is missing"
        );
    }

    @Test
    public void verifyPasswordField() {
        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='password']")).size() > 0,
            "Password field is missing"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
