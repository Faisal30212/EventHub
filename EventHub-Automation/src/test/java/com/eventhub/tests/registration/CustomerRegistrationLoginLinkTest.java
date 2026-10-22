
package com.eventhub.tests.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerRegistrationLoginLinkTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    // Verify that the registration page opens successfully.
    @Test
    public void verifyRegistrationPageOpens() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("/register"),
            "Registration page did not open"
        );
    }

    // Verify and print the links available on the registration page.
    @Test
    public void verifyRegistrationPageLinks() {
        java.util.List<WebElement> links =
            driver.findElements(By.cssSelector("a"));

        System.out.println("Registration page links: " + links.size());

        for (WebElement link : links) {
            if (link.isDisplayed()) {
                System.out.println(
                    "Link text: " + link.getText()
                    + " | URL: " + link.getAttribute("href")
                );
            }
        }

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/register"),
            "Registration page is no longer open"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

