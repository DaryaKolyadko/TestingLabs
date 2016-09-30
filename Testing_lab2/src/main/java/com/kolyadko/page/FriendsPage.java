package com.kolyadko.page;

import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 24.09.2016.
 */
public class FriendsPage extends Page {
    public static final String URL = "https://vk.com/friends";

    public FriendsPage(WebDriver driver) {
        this.driver = driver;
    }
}