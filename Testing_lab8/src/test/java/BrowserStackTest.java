import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by DaryaKolyadko on 17.12.2016.
 */
public class BrowserStackTest {
    private static final String USERNAME = "darya31";
    private static final String AUTOMATE_KEY = "ihzcsQLVFqmMHSDApkgK";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @DataProvider
    public Object[][] config() {
        return new Object[][]{
                {"IE", "7.0", "Windows", "XP", "true"},
                {"Chrome", "55.0", "Windows", "10", "true"},
                {"Firefox", "50.0", "Windows", "8.1", "true"},
                {"Safari", "7.1", "OS X", "Mavericks", "true"}
        };
    }

    @Test(dataProvider = "config")
    public void testGoogle(String browser, String browserVersion, String os, String osVersion, String debug) throws Exception {
        WebDriver driver = initWebDriver(browser, browserVersion, os, osVersion, debug);
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        driver.quit();
    }

    @Test(dataProvider = "config")
    public void testVelcom(String browser, String browserVersion, String os, String osVersion, String debug) throws Exception {
        WebDriver driver = initWebDriver(browser, browserVersion, os, osVersion, debug);
        driver.get("http://www.velcom.by/");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div/div[1]/div[1]/div[2]/table/tbody/tr/td[3]/a"));
        button.click();
        driver.quit();
    }

    private WebDriver initWebDriver(String browser, String browserVersion, String os, String osVersion, String debug) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", browser);
        caps.setCapability("browser_version", browserVersion);
        caps.setCapability("os", os);
        caps.setCapability("os_version", osVersion);
        caps.setCapability("browserstack.debug", debug);
        return new RemoteWebDriver(new URL(URL), caps);
    }
}