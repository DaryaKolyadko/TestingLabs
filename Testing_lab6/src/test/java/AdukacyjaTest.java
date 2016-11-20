import com.kolyadko.page.*;
import com.kolyadko.util.PropertiesManager;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public class AdukacyjaTest {
    private static final String TEST_SITE = "http://www.adukacyja.by";
    private static final String HAR_MARK = "adukacyja.by";
    private static final int PROXY_PORT = 4444;
    private static final String ARG = "--proxy-server=localhost:";
    private static final String DIR = "results\\";
    private static final String FILE_RESULT_LOAD = DIR + "TestLoad.har";
    private static final String FILE_RESULT_LOGIN_NEG = DIR + "TestLoginNeg.har";
    private static final String FILE_RESULT_COURSES = DIR + "TestLinkCourses.har";

    private ChromeDriver chromeDriver;
    private ProxyServer server;

    @Before
    public void beforeAllTests() {
        try {
            File file = new File(PropertiesManager.getInstance().getProperty(PropertiesManager.CHROME_DRIVER_PATH));
            System.setProperty(PropertiesManager.CHROME_DRIVER_PATH, file.getAbsolutePath());
            server = new ProxyServer(PROXY_PORT);
            ChromeOptions option = new ChromeOptions();
            option.addArguments(ARG + server.getPort());
            chromeDriver = new ChromeDriver(option);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterAllTests() {
        try {
            chromeDriver.quit();
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadAdukacyja() {
        chromeDriver.get(TEST_SITE);
        server.newHar(HAR_MARK);        // set mark for har file
        chromeDriver.get(TEST_SITE);
        saveHar(FILE_RESULT_LOAD, server.getHar());
    }


    @Test
    public void testLoginNegativeAdukacyja() throws InterruptedException {
        Page resultPage = loginAction("lalala", "lalala");
        assertTrue(resultPage instanceof LoginFailurePage);
        saveHar(FILE_RESULT_LOGIN_NEG, server.getHar());
    }

    @Test
    public void testLinkCoursesAdukacyja() {
        chromeDriver.get(TEST_SITE);
        server.newHar(HAR_MARK);        // set mark for har file
        MainPage page = new MainPage(chromeDriver);
        CoursesPage coursesPage = page.goToCoursesPage();
        assertEquals(CoursesPage.URL, chromeDriver.getCurrentUrl());
        saveHar(FILE_RESULT_COURSES, server.getHar());
    }

    private Page loginAction(String login, String password) {
        chromeDriver.get(LoginPage.URL);
        server.newHar(HAR_MARK);        // set mark for har file
        LoginPage page = new LoginPage(chromeDriver);
        page.typeLogin(login);
        page.typePassword(password);
        return page.loginFormSubmit();
    }

    private void saveHar(String fileName, Har har) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                har.writeTo(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}