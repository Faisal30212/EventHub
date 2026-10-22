
package com.eventhub.tests.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.eventhub.pages.LoginPage;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Verify that the customer login page displays its heading.
    @Test
    public void verifyLoginPageHeading() {
        Assert.assertEquals(
                loginPage.getHeading(),
                "Customer Login"
        );
    }

    // Verify that submitting an empty form displays an email validation message.
    @Test
    public void verifyEmptyEmailValidation() {
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getMessage(),
                "Please enter your email address."
        );
    }

    // Verify that submitting an email without a password displays validation.
    @Test
    public void verifyEmptyPasswordValidation() {
        loginPage.enterEmail("test@example.com");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getMessage(),
                "Please enter your password."
        );
    }

    // Verify that the password visibility button shows and hides the password.
    @Test
    public void verifyPasswordVisibilityToggle() {
        loginPage.enterPassword("TestPassword123");

        Assert.assertEquals(
                loginPage.getPasswordFieldType(),
                "password"
        );

        loginPage.clickPasswordToggle();

        Assert.assertEquals(
                loginPage.getPasswordFieldType(),
                "text"
        );

        Assert.assertEquals(
                loginPage.getPasswordToggleLabel(),
                "Hide password"
        );

        loginPage.clickPasswordToggle();

        Assert.assertEquals(
                loginPage.getPasswordFieldType(),
                "password"
        );
    }

    // Verify that the registration link points to the registration page.
    @Test
    public void verifyRegisterLink() {
        Assert.assertEquals(
                loginPage.getRegisterLink(),
                "/register"
        );
    }
}
