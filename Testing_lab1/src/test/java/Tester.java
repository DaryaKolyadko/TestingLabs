import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by DaryaKolyadko on 21.09.2016.
 */
public class Tester {
    private ChromeDriver chromeDriver;

    @Test
    public void testFirst() {
        File file = new File(PropertiesManager.getInstance().getProperty(PropertiesManager.CHROME_DRIVER_PATH));
        System.setProperty(PropertiesManager.CHROME_DRIVER_PATH, file.getAbsolutePath());
        chromeDriver = new ChromeDriver();
        chromeDriver.get("http://www.tut.by");
        chromeDriver.findElementByXPath(".//*[@id='mainmenu']/ul/li[5]/a").click();
    }
}