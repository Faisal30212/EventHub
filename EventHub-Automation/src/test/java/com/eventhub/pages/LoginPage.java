
package com.eventhub.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput =
            By.cssSelector("input[placeholder='Email Address']");

    private final By passwordInput =
            By.cssSelector("input[placeholder='Password']");

    private final By loginButton =
            By.cssSelector("button.login-btn");

    private final By message =
            By.cssSelector(".login-error-message");

    private final By passwordToggle =
            By.cssSelector("button.password-toggle");

    private final By registerLink =
            By.cssSelector("a[href='/register']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(
                "https://event-hub-eta-six.vercel.app/login");
    }

    public String getHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".login-heading h2")
                )
        ).getText();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)
        ).getText();
    }

    public void clickPasswordToggle() {
        driver.findElement(passwordToggle).click();
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordInput)
                .getDomAttribute("type");
    }

    public String getPasswordToggleLabel() {
        return driver.findElement(passwordToggle)
                .getDomAttribute("aria-label");
    }

    public String getRegisterLink() {
        return driver.findElement(registerLink)
                .getDomAttribute("href");
    }
}
