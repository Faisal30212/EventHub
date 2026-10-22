
package com.eventhub.tests.feedback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerSuggestionTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    // Verify that the EventHub homepage opens successfully.
    @Test
    public void verifyHomepageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not open"
        );
    }

    // Inspect visible forms and fields to identify the actual suggestion controls.
    @Test
    public void inspectSuggestionFormElements() {
        System.out.println("PAGE TITLE: " + driver.getTitle());

        for (WebElement element : driver.findElements(
                By.cssSelector("input, textarea, select, button, form"))) {

            if (element.isDisplayed()) {
                System.out.println(
                    "TAG: " + element.getTagName()
                    + " | TYPE: " + element.getAttribute("type")
                    + " | TEXT: " + element.getText()
                    + " | PLACEHOLDER: " + element.getAttribute("placeholder")
                    + " | NAME: " + element.getAttribute("name")
                );
            }
        }

        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage is no longer open"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
