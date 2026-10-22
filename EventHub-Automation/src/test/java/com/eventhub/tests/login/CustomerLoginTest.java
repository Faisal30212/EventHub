
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/login");
    }

    // Verify that the customer login page opens successfully.
    @Test
    public void verifyCustomerLoginPageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/login"),
            "Customer login page did not open"
        );
    }

    // Verify that the customer login page contains email and password fields.
    @Test
    public void verifyCustomerLoginFields() {
        java.util.List<WebElement> emailFields =
            driver.findElements(By.cssSelector("input[type='email']"));

        java.util.List<WebElement> passwordFields =
            driver.findElements(By.cssSelector("input[type='password']"));

        Assert.assertFalse(emailFields.isEmpty(), "Email field was not found");
        Assert.assertFalse(passwordFields.isEmpty(), "Password field was not found");
    }

    // Verify that the customer login page displays a login button.
    @Test
    public void verifyCustomerLoginButton() {
        java.util.List<WebElement> buttons =
            driver.findElements(By.tagName("button"));

        Assert.assertTrue(
            buttons.stream().anyMatch(button ->
                button.isDisplayed()
                    && button.getText().toLowerCase().contains("login")),
            "Visible Login button was not found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
