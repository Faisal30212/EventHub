
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationButtonStateTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that a registration submit button is displayed.
    @Test
    public void verifyRegisterButtonIsDisplayed() {
        WebElement button = driver.findElements(By.tagName("button"))
            .stream()
            .filter(WebElement::isDisplayed)
            .filter(element -> {
                String text = element.getText().toLowerCase();
                return text.contains("register") || text.contains("sign up");
            })
            .findFirst()
            .orElseThrow(() ->
                new AssertionError("Register button was not found"));

        Assert.assertTrue(button.isDisplayed());
    }

    // Verify that the registration submit button is enabled.
    @Test
    public void verifyRegisterButtonIsEnabled() {
        WebElement button = driver.findElements(By.tagName("button"))
            .stream()
            .filter(WebElement::isDisplayed)
            .filter(element -> {
                String text = element.getText().toLowerCase();
                return text.contains("register") || text.contains("sign up");
            })
            .findFirst()
            .orElseThrow(() ->
                new AssertionError("Register button was not found"));

        Assert.assertTrue(
            button.isEnabled(),
            "Register button is disabled"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
