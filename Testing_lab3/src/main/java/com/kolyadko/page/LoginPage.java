package com.kolyadko.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 24.09.2016.
 */
public class LoginPage extends Page {
    private By emailLocator;
    private By passwordLocator;
    private By loginButtonLocator;

    public static final String URL = "http://vk.com";

    public LoginPage(WebDriver driver) {
        emailLocator = By.id("index_email");
        passwordLocator = By.id("index_pass");
        loginButtonLocator = By.id("index_login_button");
        this.driver = driver;
    }

    public LoginPage typeEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public Page loginFormSubmit() {
        driver.findElement(loginButtonLocator).submit();
        waitFor(4);

        if (FeedPage.URL.equals(driver.getCurrentUrl())) {
            return new FeedPage(driver);
        }

        return new LoginFailurePage(driver);
    }
}