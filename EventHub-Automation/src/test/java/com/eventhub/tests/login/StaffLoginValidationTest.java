
package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginValidationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/staff-login");
    }

    // Verify that the staff login page opens successfully.
    @Test
    public void verifyStaffLoginPageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/staff-login"),
            "Staff login page did not open"
        );
    }

    // Verify that the login page displays its heading.
    @Test
    public void verifyManagerLoginHeading() {
        String pageText = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue(
            pageText.contains("Manager Login"),
            "Manager Login heading was not displayed"
        );
    }

    // Verify that the login form contains input fields.
    @Test
    public void verifyLoginInputFields() {
        java.util.List<WebElement> inputs =
            driver.findElements(By.tagName("input"));

        Assert.assertTrue(
            inputs.size() >= 2,
            "Expected login input fields were not found"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
