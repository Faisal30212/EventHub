
package com.eventhub.tests.venues;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VenueBrowsingTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get("https://event-hub-eta-six.vercel.app/venues");
    }

    // Verify that the venue page displays the correct heading.
    @Test
    public void verifyVenuePageHeading() {
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".venue-heading h1")));

        Assert.assertEquals(
                heading.getText(),
                "Find Your Perfect Venue 🏢");
    }

    // Verify that available venue cards are displayed.
    @Test
    public void verifyVenueCardsAreDisplayed() {
        List<WebElement> venues = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".venue-card"), 0));

        Assert.assertTrue(
                venues.size() > 0,
                "No venue cards were displayed");
    }

    // Verify that clicking View Venue opens the venue details page.
    @Test
    public void verifyViewVenueNavigation() {
        WebElement viewButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".venue-button")));

        viewButton.click();

        wait.until(
                ExpectedConditions.urlMatches(
                        ".*/venues/\\d+"));

        Assert.assertTrue(
                driver.getCurrentUrl().matches(".*/venues/\\d+"),
                "Venue details page did not open");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
