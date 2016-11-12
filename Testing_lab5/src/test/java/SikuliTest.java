import com.kolyadko.util.PropertiesManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;

/**
 * Created by DaryaKolyadko on 12.11.2016.
 */
public class SikuliTest {
    private static final String TEST_SITE = "https://translate.google.com";
    private static final String ENTER_WORD = "enter_word.png";
    private static final String TRANSLATE_BUTTON = "translate_button.png";
    private static final String MOTHER = "mother.png";
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
    public void testGoogleTranslator() {
        Screen screen = new Screen();
        Pattern enterWord = new Pattern(ENTER_WORD);
        Pattern translateButton = new Pattern(TRANSLATE_BUTTON);
        Pattern mother = new Pattern(MOTHER);
        chromeDriver.get(TEST_SITE);

        try {
            screen.wait(enterWord, 10);
            screen.type(enterWord, "mother");
            screen.click(translateButton);
            screen.wait(mother, 10);
        } catch (FindFailed findFailed) {
            assert false;
        }
    }
}