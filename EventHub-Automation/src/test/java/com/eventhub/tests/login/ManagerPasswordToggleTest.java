
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

public class ManagerPasswordToggleTest {

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
    }

    @Test
    public void verifyManagerCanShowAndHidePassword() {

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                ".password-input-group input")));

        WebElement toggleButton = driver.findElement(
                By.cssSelector(".password-toggle"));

        Assert.assertEquals(
                passwordInput.getAttribute("type"),
                "password",
                "Password should be hidden initially.");

        toggleButton.click();

        Assert.assertEquals(
                passwordInput.getAttribute("type"),
                "text",
                "Password should become visible after clicking the eye icon.");

        toggleButton.click();

        Assert.assertEquals(
                passwordInput.getAttribute("type"),
                "password",
                "Password should be hidden again after the second click.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
