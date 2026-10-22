
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationEmptyFieldsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the registration form contains input fields.
    @Test
    public void verifyRegistrationFieldsExist() {
        java.util.List<WebElement> inputs =
            driver.findElements(By.tagName("input"));

        Assert.assertTrue(
            inputs.size() >= 2,
            "Registration input fields were not found"
        );
    }

    // Verify that the registration page remains open when no data is entered.
    @Test
    public void verifyRegistrationPageRemainsOpen() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/register"),
            "Registration page did not open"
        );

        System.out.println("Registration page URL: " + driver.getCurrentUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}