package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public boolean pageMessage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        //Thread.sleep(5000);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[text-4xl=''][op50=''][text-center='']")));
        String pageMessageText = element.getText();
        String expectedPageMessage = "Type something to search...";
        return pageMessageText.equals(expectedPageMessage);
        //assertEquals(expectedPageMessage, pageMessageText);
    }

    public boolean searchResult(){

        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
        searchBox.clear();
        Actions actions = new Actions(driver);
        actions.sendKeys(searchBox, "Star Wars").perform();
        // Wait for the first result image to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstResultImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/movie/11')]//img[@alt='Star Wars']"))
        );

        // Get the alt attribute of the first result image
        String altText = firstResultImage.getAttribute("alt");

        if (altText != null && altText.contains("Star Wars")) {
            return true; // Test Passed
        } else {
            System.out.println("Test Failed: First search result does not contain 'Star Wars'.");
            return false;
        }
    }



}
