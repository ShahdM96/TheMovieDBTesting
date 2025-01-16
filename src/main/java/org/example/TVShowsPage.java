package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TVShowsPage {


    private WebDriver driver;
    private WebDriverWait wait;
    private static final String baseURL ="http://localhost:3000/tv";


    public TVShowsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageLoaded(){
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedPageTitle = "TV Shows · Nuxt Movies";
        return actualTitle.equals(expectedPageTitle);
    }

}
