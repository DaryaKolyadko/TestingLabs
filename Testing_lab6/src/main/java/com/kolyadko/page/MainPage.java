package com.kolyadko.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by DaryaKolyadko on 19.11.2016.
 */
public class MainPage extends Page {
    public static final String URL = "http://www.adukacyja.by";
    private By coursesLocator;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        coursesLocator = By.xpath("//*[@id=\"expandable_branch_0_courses\"]/a");
    }

    public CoursesPage goToCoursesPage() {
        driver.findElement(coursesLocator).click();
        waitFor(2);
        return new CoursesPage(driver);
    }

    public boolean tryInit() {
        try {
            driver.findElement(coursesLocator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}