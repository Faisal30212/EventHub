
package com.eventhub.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class CategoryTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the event categories section displays its correct heading.
    @Test
    public void verifyCategoryHeading() {
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".section-heading h2")));

        Assert.assertEquals(
                heading.getText(),
                "Explore Event Categories");
    }

    // Verify that six event category cards are displayed.
    @Test
    public void verifyCategoryCards() {
        List<WebElement> categories = wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector(".category-card"), 6));

        Assert.assertEquals(categories.size(), 6);
    }

    // Verify that clicking Learn More opens the Wedding Events popup.
    @Test
    public void verifyCategoryPopup() {
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".category-card button")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button);

        wait.until(
                ExpectedConditions.elementToBeClickable(button));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", button);

        WebElement popupHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".category-info-popup h2")));

        Assert.assertEquals(
                popupHeading.getText(),
                "Wedding Events");
    }

    // Verify that the category information popup closes successfully.
    @Test
    public void verifyCategoryPopupClose() {
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".category-card button")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", button);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".category-info-popup")));

        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".category-info-close")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", closeButton);

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".category-info-overlay")));

        Assert.assertTrue(
                driver.findElements(
                        By.cssSelector(".category-info-overlay")).isEmpty(),
                "Category popup did not close");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
