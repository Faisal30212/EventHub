
package com.eventhub.tests.feedback;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerReviewValidationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String REVIEW_URL =
            "https://event-hub-eta-six.vercel.app/reviews";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        driver.get(REVIEW_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".review-card form")));
    }

    private void submitReviewForm() {
        WebElement form = driver.findElement(
                By.cssSelector(".review-card form"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('submit', " +
                "{bubbles: true, cancelable: true}));",
                form);
    }

    @Test
    public void verifyEventSelectionIsRequired() {
        submitReviewForm();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".review-message")));

        Assert.assertEquals(
                message.getText().trim(),
                "Please select an event.",
                "Incorrect validation message when no event is selected.");
    }

    @Test
    public void verifyReviewCommentIsRequired() {
        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".review-card select")));

        wait.until(d ->
                dropdown.findElements(By.tagName("option")).size() > 1);

        Select eventSelect = new Select(dropdown);
        eventSelect.selectByIndex(1);

        submitReviewForm();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".review-message")));

        Assert.assertEquals(
                message.getText().trim(),
                "Please enter your comment.",
                "Incorrect validation message when the comment is empty.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
