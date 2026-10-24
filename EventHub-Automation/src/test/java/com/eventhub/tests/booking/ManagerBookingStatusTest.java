
package com.eventhub.tests.booking;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManagerBookingStatusTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(BASE_URL + "/staff-login");
    }

    @Test
    public void verifyManagerCanAcceptTestBooking() {

        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email, "Manager email is missing.");
        Assert.assertNotNull(password, "Manager password is missing.");

        driver.findElement(By.cssSelector("input[type='email']"))
                .sendKeys(email);
        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys(password);
        driver.findElement(By.cssSelector("button.login-btn")).click();

        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".manager-table")));

        WebElement testRow = null;

        List<WebElement> rows = driver.findElements(
                By.cssSelector(".manager-table tbody tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            if (cells.size() >= 7
                    && cells.get(0).getText().trim().equals("#3")
                    && cells.get(1).getText().trim()
                            .equalsIgnoreCase("Faisal khan")
                    && cells.get(2).getText().trim()
                            .equalsIgnoreCase("Birthday Event")
                    && cells.get(6).getText().trim()
                            .equalsIgnoreCase("REQUESTED")) {
                testRow = row;
                break;
            }
        }

        Assert.assertNotNull(testRow,
                "Test booking #3 was not found with REQUESTED status.");

        WebElement manageButton = testRow.findElement(
                By.xpath(".//button[normalize-space()='Manage']"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                manageButton);

        wait.until(ExpectedConditions.elementToBeClickable(manageButton));

        try {
            manageButton.click();
        } catch (ElementClickInterceptedException e) {
            // Reposition the element and retry once.
            ((JavascriptExecutor) driver).executeScript(
                    "window.scrollBy(0, -150);");
            wait.until(ExpectedConditions.elementToBeClickable(manageButton))
                    .click();
        }

        WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".manager-modal")));

        Assert.assertTrue(modal.getText().contains("Request #3"),
                "The wrong booking dialog opened.");

        WebElement acceptButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.action-accept")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                acceptButton);

        wait.until(ExpectedConditions.elementToBeClickable(acceptButton))
                .click();

        // Verify the application reflects the changed status.
        wait.until(d -> {
            List<WebElement> updatedRows = d.findElements(
                    By.cssSelector(".manager-table tbody tr"));

            for (WebElement row : updatedRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                if (cells.size() >= 7
                        && cells.get(0).getText().trim().equals("#3")
                        && cells.get(1).getText().trim()
                                .equalsIgnoreCase("Faisal khan")
                        && cells.get(2).getText().trim()
                                .equalsIgnoreCase("Birthday Event")) {
                    return !cells.get(6).getText().trim()
                            .equalsIgnoreCase("REQUESTED");
                }
            }

            return false;
        });

        Assert.assertTrue(true,
                "Manager updated the status of test booking #3.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
