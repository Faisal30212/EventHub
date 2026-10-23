
package com.eventhub.tests.login;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerDashboardAuthenticationTest {

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

        driver.get(BASE_URL);

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear();");

        driver.get(BASE_URL + "/manager-dashboard");
    }

    @Test
    public void verifyUnauthenticatedUserIsRedirected() {

        wait.until(d ->
                d.getCurrentUrl().contains("/staff-login"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/staff-login"),
                "Unauthenticated user was not redirected to staff login."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
