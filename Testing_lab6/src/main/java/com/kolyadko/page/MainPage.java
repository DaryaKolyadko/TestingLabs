package com.kolyadko.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public class MainPage extends Page {
    public static final String URL = "http://www.adukacyja.by";
    private By myCoursesLocator;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        myCoursesLocator = By.xpath("//*[@id=\"expandable_branch_0_mycourses\"]/a");
    }

    public MyCoursesPage goToMyCoursesPage() {
        driver.findElement(myCoursesLocator).click();
        waitFor(2);
        return new MyCoursesPage(driver);
    }

    public boolean tryInit() {
        try {
            driver.findElement(myCoursesLocator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}