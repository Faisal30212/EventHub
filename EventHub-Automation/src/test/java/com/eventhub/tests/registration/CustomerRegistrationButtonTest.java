
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationButtonTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the registration page contains a visible submit button.
    @Test
    public void verifyRegisterButtonIsDisplayed() {
        java.util.List<WebElement> buttons =
            driver.findElements(By.tagName("button"));

        Assert.assertTrue(
            buttons.stream().anyMatch(button ->
                button.isDisplayed()
                    && (button.getText().toLowerCase().contains("register")
                    || button.getText().toLowerCase().contains("sign up"))
            ),
            "Visible Register button was not found"
        );
    }

    // Verify that the registration button is enabled.
    @Test
    public void verifyRegisterButtonIsEnabled() {
        WebElement registerButton = driver.findElements(By.tagName("button"))
            .stream()
            .filter(button -> button.isDisplayed()
                && (button.getText().toLowerCase().contains("register")
                || button.getText().toLowerCase().contains("sign up")))
            .findFirst()
            .orElseThrow(() -> new AssertionError("Register button was not found"));

        Assert.assertTrue(
            registerButton.isEnabled(),
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
