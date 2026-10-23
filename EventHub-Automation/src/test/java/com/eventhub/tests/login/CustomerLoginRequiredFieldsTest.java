
package com.eventhub.tests.login;

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

public class CustomerLoginRequiredFieldsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(30));

        driver.get(BASE_URL + "/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']")));
    }

    @Test
    public void verifyCustomerLoginFormFields() {

        WebElement emailInput = driver.findElement(
                By.cssSelector("input[type='email']"));

        WebElement passwordInput = driver.findElement(
                By.cssSelector("input[type='password']"));

        WebElement submitButton = driver.findElement(
                By.cssSelector("button[type='submit']"));

        Assert.assertTrue(
                emailInput.isDisplayed(),
                "Email field is not displayed.");

        Assert.assertTrue(
                passwordInput.isDisplayed(),
                "Password field is not displayed.");

        Assert.assertTrue(
                submitButton.isDisplayed(),
                "Login button is not displayed.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
