
package com.eventhub.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By fullNameInput =
            By.cssSelector("input[placeholder='Enter your full name']");

    private final By emailInput =
            By.cssSelector("input[placeholder='Enter your email']");

    private final By phoneInput =
            By.cssSelector("input[placeholder='Enter 10-digit phone number']");

    private final By passwordInput =
            By.cssSelector("input[placeholder='Create a password']");

    private final By registerButton =
            By.cssSelector("button.register-button");

    private final By message =
            By.cssSelector(".register-message");

    private final By loginLink =
            By.cssSelector("a[href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://event-hub-eta-six.vercel.app/register");
    }

    public String getHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".register-header h1")
                )
        ).getText();
    }

    public void enterFullName(String name) {
        driver.findElement(fullNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)
        ).getText();
    }

    public String getLoginLink() {
        return driver.findElement(loginLink).getDomAttribute("href");
    }

    public String getPhoneValue() {
        return driver.findElement(phoneInput).getDomProperty("value");
    }

    public String getPhoneMaxLength() {
        return driver.findElement(phoneInput).getDomAttribute("maxlength");
    }
}
