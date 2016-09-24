import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by DaryaKolyadko on 21.09.2016.
 */
public class Tester {
    private static final String TEST_SITE = "http://www.velcom.by";
    private static final String TEST_ARCHIVE_PAGE = "/ru/private/campaigns/archive";
    private static final String TEST_TEXT = "some text";
    private static final String TEST_BY_LOCALE_URL = "http://www.velcom.by/be/private";

    private ChromeDriver chromeDriver;

    @BeforeTest
    public void beforeAllTests() {
        File file = new File(PropertiesManager.getInstance().getProperty(PropertiesManager.CHROME_DRIVER_PATH));
        System.setProperty(PropertiesManager.CHROME_DRIVER_PATH, file.getAbsolutePath());
        chromeDriver = new ChromeDriver();
    }

    @AfterTest
    public void afterAllTests() {
        chromeDriver.quit();
    }

    @Test
    public void testTitle() {
        chromeDriver.get(TEST_SITE);
        assertEquals("Title doesn't match", "velcom - оператор мобильной связи в Беларуси", chromeDriver.getTitle());
    }

    @Test
    public void testSearchButtonClick() {
        chromeDriver.get(TEST_SITE);
        chromeDriver.findElementByXPath("//*[@id=\"full_text_search_form\"]/input[1]").sendKeys(TEST_TEXT);
        chromeDriver.findElementByXPath("//*[@id=\"full_text_search_form\"]/input[2]").click();
        assertEquals("Search text not the same", TEST_TEXT,
                chromeDriver.findElementByXPath("//*[@id=\"full_text_search_form\"]/input[1]").getAttribute("value"));
    }

    @Test
    public void testLogIn() {
        chromeDriver.get(TEST_SITE);
        chromeDriver.findElementByXPath("//*[@id=\"right_column\"]/div[1]/div/a").click();
        chromeDriver.findElementByXPath("//*[@id=\"_next_auth\"]").click();
        assertTrue(!chromeDriver.findElementByXPath("//*[@id=\"ext-gen4\"]/td/p[2]/span").getText().isEmpty());
    }

    @Test
    public void testChangeArchivePage() {
        chromeDriver.get(TEST_SITE + TEST_ARCHIVE_PAGE);
        chromeDriver.findElementByXPath("//*[@id=\"pages\"]/a[1]").click();
        assertEquals("Page is wrong", "2",
                chromeDriver.findElementById("pages").findElement(By.tagName("b")).getText());
    }

    @Test
    public void testChangeLocale() {
        chromeDriver.get(TEST_SITE);
        chromeDriver.findElementByXPath("//*[@id=\"mainContent\"]/div[1]/div/div/div[2]/div[2]/a[1]").click();
        assertEquals("Incorrect URL", TEST_BY_LOCALE_URL, chromeDriver.getCurrentUrl());
    }
}