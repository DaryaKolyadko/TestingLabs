package com.kolyadko.page;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import static com.kolyadko.page.GeneralStepdefs.resultPage;
import static com.kolyadko.page.GeneralStepdefs.targetPage;

/**
 * Created by DaryaKolyadko on 01.10.2016.
 */
@CucumberOptions(features = {"src/main/resources/com/kolyadko/login.feature",
        "src/main/resources/com/kolyadko/open_other_page.feature"})
public class LoginStepdefs extends AbstractTestNGCucumberTests {
    private LoginPage loginPage;

    @And("^I enter email ([_A-Za-z0-9@-]+)$")
    public void iEnterEmail(String email) throws Throwable {
        getPage();
        loginPage.typeEmail(email);
    }

    @And("^I enter password ([_A-Za-z0-9@-]+)$")
    public void iEnterPassword(String password) throws Throwable {
        getPage();
        loginPage.typePassword(password);
    }

    @Then("^I push login button$")
    public void iPushLoginButton() throws Throwable {
        getPage();
        resultPage = loginPage.loginFormSubmit();
    }

    private void getPage() {
        loginPage = (LoginPage) targetPage;
    }
}