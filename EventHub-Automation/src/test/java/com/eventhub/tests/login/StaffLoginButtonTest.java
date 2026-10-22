
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginButtonTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    // Verify that the staff login page contains a login button.
    @Test
    public void verifyLoginButtonIsDisplayed() {
        java.util.List<WebElement> buttons =
            driver.findElements(By.tagName("button"));

        Assert.assertTrue(
            buttons.stream().anyMatch(button ->
                button.isDisplayed()
                && button.getText().toLowerCase().contains("login")
            ),
            "Visible Login button was not found"
        );
    }

    // Verify that the login button is enabled.
    @Test
    public void verifyLoginButtonIsEnabled() {
        WebElement loginButton = driver.findElements(By.tagName("button"))
            .stream()
            .filter(button -> button.isDisplayed()
                && button.getText().toLowerCase().contains("login"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("Login button was not found"));

        Assert.assertTrue(
            loginButton.isEnabled(),
            "Login button is disabled"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
