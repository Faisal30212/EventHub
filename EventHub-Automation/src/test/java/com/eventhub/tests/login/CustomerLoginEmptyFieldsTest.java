
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

public class CustomerLoginEmptyFieldsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String LOGIN_URL =
            "https://event-hub-eta-six.vercel.app/login";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get(LOGIN_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".login-card")));
    }

    @Test
    public void verifyEmptyLoginShowsEmailValidation() {
        driver.findElement(
                By.cssSelector(".login-btn")).click();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-error-message")));

        Assert.assertEquals(
                message.getText().trim(),
                "Please enter your email address.",
                "Incorrect validation message for empty email.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Page navigated away after empty login submission.");
    }

    @Test
    public void verifyEmptyPasswordShowsPasswordValidation() {
        driver.findElement(
                By.cssSelector("input[type='email']"))
                .sendKeys("test@example.com");

        driver.findElement(
                By.cssSelector(".login-btn")).click();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-error-message")));

        Assert.assertEquals(
                message.getText().trim(),
                "Please enter your password.",
                "Incorrect validation message for empty password.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Page navigated away after empty password submission.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
