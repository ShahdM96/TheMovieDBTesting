package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String baseURL ="http://localhost:3000";


    // Element Locators
    private By homeButton = By.xpath("//div[contains(@class, 'i-ph-house-fill')]");
    private By moviesButton = By.xpath("//div[contains(@class, 'i-ph-film-strip')]");
    private By tvShowsButton = By.xpath("//div[contains(@class, 'i-ph-television-simple')]");
    private By searchButton = By.xpath("//div[contains(@class, 'i-ph-magnifying-glass')]");
    private By languageDropdown = By.xpath("//select[@id='langSwitcher']");
    private By scrollSuggestions = By.xpath("//div[@relative]//button[@type='button' and @title='Scroll right']");



    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    public boolean isPageLoaded(){
        String actualTitle = driver.getTitle();
        String expectedPageTitle = "Nuxt Movies";
        return actualTitle.equals(expectedPageTitle);
    }

    // Navigate to Home Page
    public void clickHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
    }


    // Navigate to Movies Page
    public void clickMovies() {
        wait.until(ExpectedConditions.elementToBeClickable(moviesButton)).click();
    }

    // Navigate to TV Shows Page
    public void clickTvShows() {
        wait.until(ExpectedConditions.elementToBeClickable(tvShowsButton)).click();
    }

    // Navigate to Search Page
    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Change Language
    public void changeLanguage(String languageValue) {
        By languageOptions = By.xpath("//select[@id='langSwitcher']/option[text()='" + languageValue + "']");
        driver.findElement(languageDropdown).click();
        driver.findElement(languageOptions).click();
        driver.navigate().refresh();//Due to Bug

    }

    // Scroll through suggestions
    public void checkScrollSuggestions() throws InterruptedException {
        //List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(scrollSuggestions));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(scrollSuggestions));
        Thread.sleep(3000);
        //scroll till you find the movie "dirty angles"
        WebElement targetElement = driver.findElement(By.xpath("//a[@href='/movie/1043905']"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(targetElement).perform();
//        element.click();
//        element.click();
//        element.click();
//        element.click();
//        element.click();
//        for (WebElement suggestion : suggestions) {
//            System.out.println("Suggestion: " + suggestion.getText());
//        }
    }

    // Scroll to an element using JavaScript
    public void scrollToElement(By elementLocator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        element.click();
        element.click();
        element.click();
        element.click();
        element.click();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public class Main {

    }
}
