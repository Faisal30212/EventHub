
package com.eventhub.tests.booking;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerBookingStatusTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String STAFF_LOGIN_URL =
            "https://event-hub-eta-six.vercel.app/staff-login";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        driver.get(STAFF_LOGIN_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".login-card")));
    }

    @Test
    public void verifyStaffLoginPageLoads() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Staff login page did not open.");

        Assert.assertEquals(
                driver.findElement(
                        By.cssSelector(".login-heading h2"))
                     .getText().trim(),
                "Manager Login",
                "Unexpected login page heading.");
    }

    @Test
    public void verifyLoginFormFieldsExist() {
        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='email']"))
                     .isDisplayed(),
                "Manager email field is not visible.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='password']"))
                     .isDisplayed(),
                "Manager password field is not visible.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("button.login-btn"))
                     .isDisplayed(),
                "Manager Login button is not visible.");
    }

    @Test
    public void verifyEmptyCredentialsValidation() {
        driver.findElement(
                By.cssSelector("button.login-btn")).click();

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-error-message")));

        Assert.assertEquals(
                errorMessage.getText().trim(),
                "Please enter email and password.",
                "Incorrect empty-credentials validation message.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Page navigated away after empty login submission.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
