
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

public class ManagerLoginValidationTest {

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

        driver.get(BASE_URL + "/staff-login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("form")));
    }

    @Test
    public void verifyEmptyManagerLoginValidation() {
        WebElement loginButton = driver.findElement(
                By.cssSelector("button.login-btn"));

        loginButton.click();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-card > div:last-child")));

        String pageText = driver.findElement(By.tagName("body"))
                .getText();

        Assert.assertTrue(
                pageText.contains("Please enter email and password."),
                "Empty login validation message was not displayed."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
