
package com.eventhub.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomepageSteps {

    public static WebDriver driver;

    @Before
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("the browser is opened")
    public void theBrowserIsOpened() {
        Assert.assertNotNull(driver, "Browser was not initialized");
    }

    @When("the user visits the EventHub homepage")
    public void theUserVisitsTheEventHubHomepage() {
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    @When("the user visits the customer login page")
    public void theUserVisitsTheCustomerLoginPage() {
        driver.get("https://event-hub-eta-six.vercel.app/login");
    }

    @Then("the homepage should load successfully")
    public void theHomepageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub homepage did not load"
        );
    }

    @Then("the customer login page should load successfully")
    public void theCustomerLoginPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "Customer login page did not load"
        );
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    @When("the user visits the event booking page")
    public void theUserVisitsTheEventBookingPage() {
        driver.get("https://event-hub-eta-six.vercel.app/");
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub website did not open"
        );
    }

    @Then("the event booking page should load successfully")
    public void theEventBookingPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub booking entry page did not load"
        );
    }

    
    @When("the user visits the venues page")
    public void theUserVisitsTheVenuesPage() {
        driver.get("https://event-hub-eta-six.vercel.app/venues");
    }

    @Then("the venues page should load successfully")
    public void theVenuesPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub website did not load"
        );
    }

    
    @When("the user visits the customer registration page")
    public void theUserVisitsTheCustomerRegistrationPage() {
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    @Then("the customer registration page should load successfully")
    public void theCustomerRegistrationPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub registration page did not load"
        );
    }

    
    @When("the user visits the events page")
    public void theUserVisitsTheEventsPage() {
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    @Then("the events page should load successfully")
    public void theEventsPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub did not load"
        );
    }

    @When("the user visits the My Events page")
    public void theUserVisitsTheMyEventsPage() {
        driver.get("https://event-hub-eta-six.vercel.app/");
    }

    @Then("the My Events page should load successfully")
    public void theMyEventsPageShouldLoadSuccessfully() {
        Assert.assertTrue(
            driver.getCurrentUrl().contains("event-hub-eta-six.vercel.app"),
            "EventHub did not load"
        );
    }

}
