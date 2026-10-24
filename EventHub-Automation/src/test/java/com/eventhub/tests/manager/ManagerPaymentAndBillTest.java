
package com.eventhub.tests.manager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

public class ManagerPaymentAndBillTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @Test
    public void acceptPaymentIfNeededAndOpenBill() {

        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email, "Manager email environment variable is missing.");
        Assert.assertNotNull(password, "Manager password environment variable is missing.");

        // 1. Login using the existing working selectors.
        driver.get(BASE_URL + "/staff-login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']"))).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='password']"))).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.login-btn"))).click();

        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));

        // 2. Open Birthday Event Request #3.
        WebElement manageButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                        "//tr[.//*[contains(normalize-space(.),'Birthday Event')]]"
                        + "//button[contains(normalize-space(.),'Manage')]")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                manageButton);

        try {
            manageButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", manageButton);
        }

        WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".manager-modal")));

        Assert.assertTrue(modal.getText().contains("Request #3"),
                "Expected Request #3.");

        // 3. If payment is not completed, click the payment button.
        // If payment was already completed, skip this step.
        List<WebElement> paymentButtons = driver.findElements(
                By.cssSelector(".manager-modal button.action-payment"));

        boolean paymentButtonVisible = false;

        for (WebElement button : paymentButtons) {
            if (button.isDisplayed()
                    && button.isEnabled()
                    && button.getText().contains("Office Payment Received")) {
                paymentButtonVisible = true;
                break;
            }
        }

        if (paymentButtonVisible) {
            WebElement paymentButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//div[contains(@class,'manager-modal')]"
                                    + "//button[contains(@class,'action-payment')"
                                    + " and contains(.,'Office Payment Received')]")));

            paymentButton.click();

            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.cssSelector(".manager-modal"),
                    "Payment completed successfully"));
        }

        // 4. Find View Bill. If payment was already completed,
        // this button should be available immediately.
        WebElement viewBillButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//div[contains(@class,'manager-modal')]"
                                + "//button[contains(@class,'action-payment')"
                                + " and contains(.,'View Bill')]")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                viewBillButton);

        viewBillButton.click();

        // 5. Verify the bill page opened for Request #3.
        wait.until(ExpectedConditions.urlContains("/bill/3"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")));

        String billText = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue(
                billText.toLowerCase().contains("bill")
                        || billText.contains("EH-BILL-3"),
                "Bill page opened, but expected bill content was not found.");

        System.out.println(
                "PASS: Request #3 bill opened successfully.");
        System.out.println("Bill URL: " + driver.getCurrentUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
