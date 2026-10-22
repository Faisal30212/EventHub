
package com.eventhub.tests.datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLoginValidationTest {

    private WebDriver driver;

    @DataProvider(name = "invalidLoginInputs")
    public Object[][] invalidLoginInputs() {
        return new Object[][] {
            {"", ""},
            {"invalid@example.com", ""},
            {"", "wrongpassword"},
            {"invalid@example.com", "wrongpassword"}
        };
    }

    @Test(dataProvider = "invalidLoginInputs")
    public void verifyLoginInputCombinations(String email, String password) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/login");

        WebElement emailField = driver.findElement(
            By.cssSelector("input[type='email'], input[name='email']")
        );
        WebElement passwordField = driver.findElement(
            By.cssSelector("input[type='password'], input[name='password']")
        );

        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        Assert.assertTrue(emailField.isDisplayed(),
                "Email field should remain visible");
        Assert.assertTrue(passwordField.isDisplayed(),
                "Password field should remain visible");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
