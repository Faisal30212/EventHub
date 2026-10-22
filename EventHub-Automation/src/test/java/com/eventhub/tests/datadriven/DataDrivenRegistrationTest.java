
package com.eventhub.tests.datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenRegistrationTest {

    private WebDriver driver;

    @DataProvider(name = "registrationData")
    public Object[][] registrationData() {
        return new Object[][] {
            {"", "", ""},
            {"Faisal Test", "", "Password123"},
            {"", "invalid@example.com", "Password123"},
            {"Faisal Test", "invalid@example.com", ""}
        };
    }

    @Test(dataProvider = "registrationData")
    public void verifyRegistrationFormInputs(
            String name, String email, String password) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");

        WebElement nameField = driver.findElement(
            By.cssSelector("input[name='name'], input[placeholder*='name' i], input[autocomplete='name']")
        );

        WebElement emailField = driver.findElement(
            By.cssSelector("input[type='email'], input[name='email']")
        );

        WebElement passwordField = driver.findElement(
            By.cssSelector("input[type='password'], input[name='password']")
        );

        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        Assert.assertTrue(nameField.isDisplayed(),
                "Name field should be visible");
        Assert.assertTrue(emailField.isDisplayed(),
                "Email field should be visible");
        Assert.assertTrue(passwordField.isDisplayed(),
                "Password field should be visible");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
