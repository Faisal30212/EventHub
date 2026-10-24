package com.eventhub.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaffLoginPasswordInputTest {

    private WebDriver driver;
    private final String baseUrl =
            "https://event-hub-eta-six.vercel.app/staff-login";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();

        // Avoid the Chrome maximize timeout
        driver.manage().window().setSize(new Dimension(1366, 768));

        driver.get(baseUrl);
    }

    @Test
    public void verifyPasswordFieldAcceptsInput() {
        WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
        );

        passwordField.sendKeys("TestPassword123");

        Assert.assertEquals(
                passwordField.getAttribute("value"),
                "TestPassword123",
                "Password field should accept input"
        );
    }

    @Test
    public void verifyPasswordFieldCanBeCleared() {
        WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
        );

        passwordField.sendKeys("TestPassword123");
        passwordField.clear();

        Assert.assertEquals(
                passwordField.getAttribute("value"),
                "",
                "Password field should be clearable"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}