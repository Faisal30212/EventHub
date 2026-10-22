
package com.eventhub.tests.venues;

import java.time.Duration;

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

public class VenueDetailsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get(
                "https://event-hub-eta-six.vercel.app/venues/3");
    }

    // Verify that the venue details page displays a venue name.
    @Test
    public void verifyVenueName() {
        WebElement venueName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".venue-details-content h1")));

        Assert.assertFalse(
                venueName.getText().trim().isEmpty(),
                "Venue name was not displayed");
    }

    // Verify that the venue details page displays its capacity information.
    @Test
    public void verifyVenueCapacity() {
        WebElement details = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".venue-details-info")));

        Assert.assertTrue(
                details.getText().contains("Capacity"),
                "Venue capacity information was not displayed");
    }

    // Verify that the venue details page displays its price information.
    @Test
    public void verifyVenuePrice() {
        WebElement details = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".venue-details-info")));

        Assert.assertTrue(
                details.getText().contains("Price Per Day"),
                "Venue price information was not displayed");
    }

    // Verify that the Back to Venues button returns to the venue listing.
    @Test
    public void verifyBackToVenuesButton() {
        WebElement backButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".venue-details-back")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                backButton);

        wait.until(
                ExpectedConditions.elementToBeClickable(backButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                backButton);

        wait.until(
                ExpectedConditions.urlToBe(
                        "https://event-hub-eta-six.vercel.app/venues"));

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/venues"),
                "The venue listing page did not open");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
