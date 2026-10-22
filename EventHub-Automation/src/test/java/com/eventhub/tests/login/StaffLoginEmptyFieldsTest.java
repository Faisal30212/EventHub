
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginEmptyFieldsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    // Verify that the staff login page opens with empty fields.
    @Test
    public void verifyEmptyLoginFields() {
        java.util.List<WebElement> inputs =
            driver.findElements(By.tagName("input"));

        Assert.assertTrue(
            inputs.size() >= 2,
            "Expected login input fields were not found"
        );

        for (WebElement input : inputs) {
            if (input.isDisplayed()) {
                Assert.assertEquals(
                    input.getAttribute("value") == null
                        ? "" : input.getAttribute("value"),
                    "",
                    "A login input field is not empty initially"
                );
            }
        }
    }

    // Verify that submitting the empty login form does not leave the login page.
    @Test
    public void verifyEmptyFormSubmission() {
        String originalUrl = driver.getCurrentUrl();

        java.util.List<WebElement> buttons =
            driver.findElements(By.tagName("button"));

        WebElement loginButton = buttons.stream()
            .filter(button -> button.isDisplayed()
                && button.getText().toLowerCase().contains("login"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("Login button was not found"));

        loginButton.click();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/staff-login")
                || driver.getCurrentUrl().equals(originalUrl),
            "Submitting the empty form navigated away from the login page"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
