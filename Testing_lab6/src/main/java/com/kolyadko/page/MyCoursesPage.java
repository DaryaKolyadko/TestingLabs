package com.kolyadko.page;

import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public class MyCoursesPage extends Page {
    public static final String URL = "http://www.adukacyja.by/my/";

    public MyCoursesPage(WebDriver driver) {
        this.driver = driver;
    }
}