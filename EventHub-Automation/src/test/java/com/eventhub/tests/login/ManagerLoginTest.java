
package com.eventhub.tests.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String LOGIN_URL =
            "https://event-hub-eta-six.vercel.app/staff-login";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(25));

        driver.get(LOGIN_URL);
    }

    @Test
    public void verifyManagerLoginPageTitle() {
        Assert.assertFalse(
                driver.getTitle().trim().isEmpty(),
                "Login page title is empty.");
    }

    @Test
    public void verifyManagerLoginHeading() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".login-heading h2")));

        Assert.assertEquals(
                driver.findElement(
                        By.cssSelector(".login-heading h2"))
                     .getText().trim(),
                "Manager Login",
                "Unexpected manager login heading.");
    }

    @Test
    public void verifyEmailAndPasswordInputs() {
        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='email']"))
                     .isDisplayed(),
                "Email input is not visible.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("input[type='password']"))
                     .isDisplayed(),
                "Password input is not visible.");
    }

    @Test
    public void verifySuccessfulManagerLogin() {
        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email,
                "Set EVENTHUB_MANAGER_EMAIL before running this test.");
        Assert.assertNotNull(password,
                "Set EVENTHUB_MANAGER_PASSWORD before running this test.");

        driver.findElement(By.cssSelector("input[type='email']"))
                .sendKeys(email);

        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys(password);

        driver.findElement(By.cssSelector("button.login-btn"))
                .click();

        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/manager-dashboard"),
                "Successful manager login did not open the dashboard.");

        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
        .executeScript(
                "return localStorage.getItem('eventhubUser')") != null);
        }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
