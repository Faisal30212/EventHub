
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

public class CustomerPasswordToggleTest {

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
                By.cssSelector("input[type='password']")));
    }

    @Test
    public void verifyCustomerPasswordVisibilityToggle() {

        WebElement passwordInput = driver.findElement(
                By.cssSelector("input[type='password']"));

        Assert.assertEquals(
                passwordInput.getAttribute("type"),
                "password",
                "Password should be hidden initially.");

        WebElement toggleButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".password-toggle")));

        toggleButton.click();

        WebElement visiblePasswordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='text']")));

        Assert.assertEquals(
                visiblePasswordInput.getAttribute("type"),
                "text",
                "Password should be visible after clicking the toggle.");

        toggleButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='password']")));

        Assert.assertEquals(
                driver.findElement(
                        By.cssSelector("input[type='password']"))
                        .getAttribute("type"),
                "password",
                "Password should be hidden again.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
