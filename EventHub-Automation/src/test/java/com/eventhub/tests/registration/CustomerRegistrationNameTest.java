
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationNameTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the name field accepts a customer's name.
    @Test
    public void verifyNameFieldAcceptsInput() {
        WebElement nameField = driver.findElements(By.cssSelector(
            "input[name='name'], input[placeholder*='name' i], input[autocomplete='name']"
        )).stream()
         .findFirst()
         .orElseThrow(() -> new AssertionError("Name field was not found"));

        nameField.sendKeys("Test Customer");

        Assert.assertEquals(
            nameField.getAttribute("value"),
            "Test Customer",
            "Name field did not accept the entered value"
        );
    }

    // Verify that the name field can be cleared.
    @Test
    public void verifyNameFieldCanBeCleared() {
        WebElement nameField = driver.findElements(By.cssSelector(
            "input[name='name'], input[placeholder*='name' i], input[autocomplete='name']"
        )).stream()
         .findFirst()
         .orElseThrow(() -> new AssertionError("Name field was not found"));

        nameField.sendKeys("Test Customer");
        nameField.clear();

        Assert.assertEquals(
            nameField.getAttribute("value"),
            "",
            "Name field was not cleared"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
