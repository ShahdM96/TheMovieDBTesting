package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String baseURL ="http://localhost:3000/search";


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //driver.manage().window().maximize();
        //driver.get(baseURL);
    }

    public boolean isPageLoaded(){
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedPageTitle = "Search: · Nuxt Movies";
        return actualTitle.equals(expectedPageTitle);
    }



}
