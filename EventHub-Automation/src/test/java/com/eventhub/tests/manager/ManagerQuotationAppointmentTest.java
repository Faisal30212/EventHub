
package com.eventhub.tests.manager;

import java.time.Duration;
import java.time.LocalDate;
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

public class ManagerQuotationAppointmentTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL =
            "https://event-hub-eta-six.vercel.app";

    private static final String TEST_NOTES =
            "Selenium test: customer discussion and event requirements.";

    private static final String TEST_QUOTE_DETAILS =
            "Selenium test quotation: venue, decoration, catering and sound system.";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @Test
    public void verifyAppointmentAndQuotationSubmission() {

        String email = System.getenv("EVENTHUB_MANAGER_EMAIL");
        String password = System.getenv("EVENTHUB_MANAGER_PASSWORD");

        Assert.assertNotNull(email,
                "Set EVENTHUB_MANAGER_EMAIL in CMD.");
        Assert.assertNotNull(password,
                "Set EVENTHUB_MANAGER_PASSWORD in CMD.");

        driver.get(BASE_URL + "/staff-login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']"))).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='password']"))).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.login-btn"))).click();

        wait.until(ExpectedConditions.urlContains("/manager-dashboard"));

        WebElement manageButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                        "//tr[.//*[contains(normalize-space(.),'Birthday Event')]]" +
                        "//button[contains(normalize-space(.),'Manage')]")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", manageButton);

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

        // Use the exact date input selector copied from DevTools.
        WebElement dateInput = modal.findElement(By.cssSelector(
                ".manager-action-section .manager-form-row " +
                "div:nth-child(1) > input[type='date']"));

        WebElement timeInput = modal.findElement(
                By.cssSelector("input[type='time']"));

        List<WebElement> textareas = modal.findElements(By.tagName("textarea"));

        Assert.assertTrue(textareas.size() >= 2,
                "Expected meeting notes and quote details fields.");

        WebElement discussion = textareas.get(0);
        WebElement quoteDetails = textareas.get(1);

        WebElement quoteAmount = modal.findElement(
                By.cssSelector("input[type='number']"));

        String appointmentDate = LocalDate.now().plusDays(7).toString();

        // Set the native date value and trigger input/change events.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "const el = arguments[0];" +
                "const setter = Object.getOwnPropertyDescriptor(" +
                "HTMLInputElement.prototype, 'value').set;" +
                "setter.call(el, arguments[1]);" +
                "el.dispatchEvent(new Event('input', {bubbles:true}));" +
                "el.dispatchEvent(new Event('change', {bubbles:true}));",
                dateInput, appointmentDate);

        String actualDate = dateInput.getAttribute("value");

        Assert.assertEquals(actualDate, appointmentDate,
                "Appointment date input did not accept the expected date.");

        // Populate appointment time.
        js.executeScript(
                "const el = arguments[0];" +
                "const setter = Object.getOwnPropertyDescriptor(" +
                "HTMLInputElement.prototype, 'value').set;" +
                "setter.call(el, arguments[1]);" +
                "el.dispatchEvent(new Event('input', {bubbles:true}));" +
                "el.dispatchEvent(new Event('change', {bubbles:true}));",
                timeInput, "11:00");

        discussion.clear();
        discussion.sendKeys(TEST_NOTES);

        quoteAmount.clear();
        quoteAmount.sendKeys("150000");

        quoteDetails.clear();
        quoteDetails.sendKeys(TEST_QUOTE_DETAILS);

        Assert.assertEquals(timeInput.getAttribute("value"), "11:00",
                "Appointment time was not populated.");

        Assert.assertEquals(quoteAmount.getAttribute("value"), "150000",
                "Quotation amount was not populated.");

        Assert.assertEquals(discussion.getAttribute("value"), TEST_NOTES,
                "Meeting notes were not populated.");

        Assert.assertEquals(quoteDetails.getAttribute("value"),
                TEST_QUOTE_DETAILS,
                "Quote details were not populated.");

        WebElement fixButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.action-appointment")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", fixButton);

        fixButton.click();

        System.out.println("Submission triggered for Request #3.");
        System.out.println("Appointment date: " + appointmentDate);
        System.out.println("Appointment time: 11:00");
        System.out.println("Quotation amount: 150000");
        System.out.println(driver.findElement(By.tagName("body")).getText());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
