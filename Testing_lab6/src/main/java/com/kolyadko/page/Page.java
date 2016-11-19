package com.kolyadko.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.concurrent.TimeUnit;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public abstract class Page {
    protected WebDriver driver;

    protected void waitFor(int seconds) {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(seconds, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}