
package com.eventhub.tests.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerBookingFormTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the homepage provides a way to begin event booking.
    @Test
    public void verifyBookingEntryPoint() {
        java.util.List<WebElement> links =
            driver.findElements(By.cssSelector("a"));

        boolean bookingLinkFound = links.stream()
            .filter(WebElement::isDisplayed)
            .anyMatch(link -> {
                String text = link.getText().toLowerCase();
                String href = link.getAttribute("href");
                return text.contains("book")
                    || text.contains("booking")
                    || (href != null && href.toLowerCase().contains("booking"));
            });

        Assert.assertTrue(
            bookingLinkFound,
            "No visible booking entry point was found on the homepage"
        );
    }

    // Verify that the homepage displays event content for customers.
    @Test
    public void verifyEventContentDisplayed() {
        String pageText = driver.findElement(By.tagName("body"))
                                .getText();

        Assert.assertFalse(
            pageText.trim().isEmpty(),
            "Homepage contains no visible content"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
