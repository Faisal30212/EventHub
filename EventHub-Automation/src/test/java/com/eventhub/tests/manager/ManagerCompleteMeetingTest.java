
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

public class ManagerCompleteMeetingTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    private static final String NOTES =
            "Selenium test: customer discussion and event requirements.";

    private static final String QUOTE_AMOUNT = "150000";

    private static final String QUOTE_DETAILS =
            "Selenium test quotation: venue, decoration, catering and sound system.";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @Test
    public void completeMeetingAndGenerateQuote() {

        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email, "Set EVENTHUB_MANAGER_EMAIL in CMD.");
        Assert.assertNotNull(password, "Set EVENTHUB_MANAGER_PASSWORD in CMD.");

        // 1. Login
        driver.get(BASE_URL + "/staff-login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']"))).sendKeys(email);

        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.login-btn"))).click();

        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));

        // 2. Open Request #3 using the working selector
        WebElement manageButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                        "//tr[.//*[contains(normalize-space(.),'Birthday Event')]]"
                        + "//button[contains(normalize-space(.),'Manage')]")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", manageButton);

        try {
            manageButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", manageButton);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".manager-modal")));

        Assert.assertTrue(
                driver.findElement(By.cssSelector(".manager-modal"))
                        .getText().contains("Request #3"),
                "Expected Request #3.");

        // 3. Complete Meeting, if that action is available
        List<WebElement> meetingButtons = driver.findElements(
                By.cssSelector(".manager-modal button.action-meeting"));

        if (!meetingButtons.isEmpty()
                && meetingButtons.get(0).isDisplayed()
                && meetingButtons.get(0).isEnabled()) {

            List<WebElement> textareas = driver.findElements(
                    By.cssSelector(
                            ".manager-modal .manager-action-section textarea"));

            Assert.assertTrue(textareas.size() >= 1,
                    "Meeting notes field was not found.");

            WebElement notesField = textareas.get(0);
            notesField.clear();
            notesField.sendKeys(NOTES);

            WebElement completeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(
                                    ".manager-modal button.action-meeting")));

            completeButton.click();

            // Wait for the meeting update to finish.
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".manager-modal button.action-quote")));
        }

        // 4. Enter the final quote
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(
                                ".manager-modal .manager-action-section "
                                + "input[type='number']")));

        List<WebElement> quoteTextareas = driver.findElements(
                By.cssSelector(
                        ".manager-modal .manager-action-section textarea"));

        Assert.assertTrue(quoteTextareas.size() >= 2,
                "Quote details field was not found.");

        amountField.clear();
        amountField.sendKeys(QUOTE_AMOUNT);

        WebElement detailsField = quoteTextareas.get(1);
        detailsField.clear();
        detailsField.sendKeys(QUOTE_DETAILS);

        Assert.assertEquals(amountField.getAttribute("value"), QUOTE_AMOUNT);
        Assert.assertEquals(detailsField.getAttribute("value"), QUOTE_DETAILS);

        // 5. Click Generate Quote
        WebElement generateButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                ".manager-modal button.action-quote")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", generateButton);

        generateButton.click();

        // 6. Verify quote generation
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"), "QUOTE_GENERATED"));

        System.out.println(
                "PASS: Meeting action handled and quote generated.");
        System.out.println("Final quote: ₹" + QUOTE_AMOUNT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
