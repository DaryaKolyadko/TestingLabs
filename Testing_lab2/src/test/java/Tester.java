import com.kolyadko.page.*;
import com.kolyadko.util.PropertiesManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by DaryaKolyadko on 21.09.2016.
 */
public class Tester {
    private static final String TEST_SITE = "https://vk.com";
    private ChromeDriver chromeDriver;

    @Before
    public void beforeAllTests() {
        File file = new File(PropertiesManager.getInstance().getProperty(PropertiesManager.CHROME_DRIVER_PATH));
        System.setProperty(PropertiesManager.CHROME_DRIVER_PATH, file.getAbsolutePath());
        chromeDriver = new ChromeDriver();
    }

    @After
    public void afterAllTests() {
        chromeDriver.quit();
    }

    @Test
    public void testLoginPositive() {
        chromeDriver.get(TEST_SITE);
        LoginPage page = new LoginPage(chromeDriver);
        page.typeEmail("enter_correct_email");
        page.typePassword("enter_correct_password");
        Page resultPage = page.loginFormSubmit();
        assertTrue(resultPage instanceof FeedPage);
        FriendsPage friendsPage = ((FeedPage) resultPage).goToFriendsPage();
        assertEquals(FriendsPage.URL, chromeDriver.getCurrentUrl());
    }

    @Test
    public void testLoginNegative() {
        chromeDriver.get(TEST_SITE);
        LoginPage page = new LoginPage(chromeDriver);
        page.typeEmail("lalala");
        page.typePassword("lalala");
        Page resultPage = page.loginFormSubmit();
        assertTrue(resultPage instanceof LoginFailurePage);
    }
}