package com.kolyadko.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 24.09.2016.
 */
public class FeedPage extends Page {
    private By friendsLocator;

    public static final String URL = "https://vk.com/feed";

    public FeedPage(WebDriver driver) {
        this.driver = driver;
        friendsLocator = By.xpath("//*[@id=\"l_fr\"]/a");
    }

    public FriendsPage goToFriendsPage() {
        driver.findElement(friendsLocator).click();
        waitFor(2);
        return new FriendsPage(driver);
    }
}