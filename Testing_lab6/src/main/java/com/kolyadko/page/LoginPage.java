package com.kolyadko.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public class LoginPage extends Page {
    private By loginLocator;
    private By passwordLocator;
    private By loginButtonLocator;

    public static final String URL = "http://www.adukacyja.by/login/index.php";

    public LoginPage(WebDriver driver) {
        loginLocator = By.id("username");
        passwordLocator = By.id("password");
        loginButtonLocator = By.id("loginbtn");
        this.driver = driver;
    }

    public LoginPage typeLogin(String login) {
        driver.findElement(loginLocator).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public Page loginFormSubmit() {
        driver.findElement(loginButtonLocator).submit();
        waitFor(6);
        MainPage mainPage = new MainPage(driver);

        if (mainPage.tryInit()) {
            return mainPage;
        }

        return new LoginFailurePage(driver);
    }
}