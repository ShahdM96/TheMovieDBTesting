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
    public MoviePage clickMovies() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(moviesButton)).click();
        Thread.sleep(3000);
        MoviePage moviePage = new MoviePage(driver);
        return moviePage;
    }

    // Navigate to TV Shows Page
    public TVShowsPage clickTvShows() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(tvShowsButton)).click();
        Thread.sleep(3000);
        TVShowsPage tvshowsPage = new TVShowsPage(driver);
        return  tvshowsPage;
    }

    // Navigate to Search Page
    public SearchPage clickSearch() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(3000);
        SearchPage searchpage = new SearchPage(driver);
        return searchpage;
    }

    // Change Language
    public boolean changeLanguage(String languageValue) {
        By languageOption = By.xpath("//select[@id='langSwitcher']/option[text()='" + languageValue + "']");
        driver.findElement(languageDropdown).click();
        driver.findElement(languageOption).click();
        return checkLanguageChange(languageValue);
        //driver.navigate().refresh();//Due to Bug
    }

    public boolean checkLanguageChange(String language){
        //driver.get(baseURL);
        //driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.xpath("//div[@data-v-inspector='components/carousel/Base.vue:21:5']"));
        String actualText = element.getText();
        String englishText = "Popular Movies";
        String expectedText = englishText;
        String germanText = "Beliebte Filme";
        String spanishText = "Películas populares";
        String italianText = "Film Popolari";
        String japaneseText = "人気の映画";
        String chineseText = "热门电影";
        String portugueseText = "Filmes populares";
        String portugueseBrazilText = "Filmes Populares";
        String russianText = "Популярные фильмы";
        String frenchText = "Films Populaires";
        String ukrainianText = "Популярні фільми";
        switch (language) {
            case "English": expectedText = englishText;
            case "Deutsch": expectedText = germanText;
            case "Español": expectedText = spanishText;
            case "Italiano": expectedText = italianText;
            case "日本語": expectedText = japaneseText;
            case "简体中文": expectedText = chineseText;
            case "Português": expectedText = portugueseText;
            case "Português do Brasil": expectedText = portugueseBrazilText;
            case "Русский": expectedText = russianText;
            case "Français": expectedText = frenchText;
            case "Українська": expectedText = ukrainianText;
            default: expectedText = englishText;
        }
        System.out.println("actual text is:" + actualText);
        System.out.println("expected text is:" + expectedText);

        return actualText.equals(expectedText);
    }

    // Scroll through suggestions
    public boolean scrollToElement() throws InterruptedException {
        //List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(scrollSuggestions));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(scrollSuggestions));
        Thread.sleep(3000);
        //Scroll till you find the movie "dirty angles"
        WebElement targetElement = driver.findElement(By.xpath("//a[@href='/movie/1043905']"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(targetElement).perform();
        //Return if the target movie is displayed on the screen
        return targetElement.isDisplayed();

        // Assert that the element is displayed
        //Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page!");
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
    public void checkScrollSuggestions(By elementLocator) {
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
