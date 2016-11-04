package com.kolyadko.page;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import static com.kolyadko.page.GeneralStepdefs.resultPage;
import static com.kolyadko.page.GeneralStepdefs.targetPage;

/**
 * Created by DaryaKolyadko on 01.10.2016.
 */
@CucumberOptions(features = {"src/main/resources/com/kolyadko/open_other_page.feature"})
public class FriendsPageStepdefs extends AbstractTestNGCucumberTests {
    private FeedPage feedPage;


    @Then("^I click on friends page$")
    public void iPushLoginButton() throws Throwable {
        getPage();
        resultPage = feedPage.goToFriendsPage();
    }

    private void getPage() {
        feedPage = (FeedPage) resultPage;
    }
}