
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    @Test
    public void verifyManagerLoginPageTitle() {
        Assert.assertFalse(
            driver.getTitle().trim().isEmpty(),
            "Login page title is empty"
        );
    }

    @Test
    public void verifyManagerLoginHeading() {
        String pageText = driver.findElement(By.tagName("body"))
                                 .getText()
                                 .toLowerCase();

        Assert.assertTrue(
            pageText.contains("manager login"),
            "Manager Login heading was not found"
        );
    }

    @Test
    public void verifyEmailAndPasswordInputs() {
        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='email']")).size() > 0,
            "Email input was not found"
        );

        Assert.assertTrue(
            driver.findElements(By.cssSelector("input[type='password']")).size() > 0,
            "Password input was not found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
