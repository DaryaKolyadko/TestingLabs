package com.kolyadko.page;

import com.kolyadko.util.PropertiesManager;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by DaryaKolyadko on 01.10.2016.
 */
@CucumberOptions(features = {"src/main/resources/com/kolyadko/login.feature",
        "src/main/resources/com/kolyadko/open_other_page.feature"})
public class GeneralStepdefs extends AbstractTestNGCucumberTests {
    private static final String TEST_SITE = "https://vk.com";
    private static final String PAGE_CLASS_PACKAGES = "com.kolyadko.page.";

    static ChromeDriver chromeDriver;
    static Page resultPage;
    static Page targetPage;

    @Given("^I open the browser$")
    public void iOpenTheBrowser() throws Throwable {
        File file = new File(PropertiesManager.getInstance().getProperty(PropertiesManager.CHROME_DRIVER_PATH));
        System.setProperty(PropertiesManager.CHROME_DRIVER_PATH, file.getAbsolutePath());
        chromeDriver = new ChromeDriver();
    }

    @Then("^Then the result is (\\w+)$")
    public void thenTheResultIsResultPage(String className) throws Throwable {
        assertEquals(className, resultPage.getClass().getSimpleName());
    }

    @Then("^I close browser$")
    public void iCloseBrowser() throws Throwable {
        chromeDriver.quit();
    }

    @When("^I open test site$")
    public void iOpenTestSite() throws Throwable {
        chromeDriver.get(TEST_SITE);
    }

    @When("^I navigate to the (\\w+)$")
    public void iNavigateToTheTargetPage(String className) throws Throwable {
        targetPage = (Page) Class.forName(PAGE_CLASS_PACKAGES + className).getConstructor
                (WebDriver.class).newInstance(chromeDriver);
    }
}