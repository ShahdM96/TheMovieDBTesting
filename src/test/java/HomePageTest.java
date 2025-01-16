import org.example.MoviePage;
import org.example.SearchPage;
import org.example.TVShowsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.example.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {
    private WebDriver driver;
    HomePage home;



    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        home = new HomePage(driver);
    }


    @Test
    void testHomeButton(){
        home.clickHome();
        assertTrue(home.isPageLoaded());
    }


    @Test
    void testMoviesButton() throws InterruptedException {
        MoviePage moviepage = home.clickMovies();
        assertTrue(moviepage.isPageLoaded());
    }


    @Test
    void testTVShowsButton() throws InterruptedException {
        TVShowsPage tvshowspage = home.clickTvShows();
        assertTrue(tvshowspage.isPageLoaded());

    }

    @Test
    void testSearchButton() throws InterruptedException {
        SearchPage searchpage = home.clickSearch();
        assertTrue(searchpage.isPageLoaded());

        Thread.sleep(5000);

    }


    @Test
    void testChangeLanguage() throws InterruptedException {
        home.changeLanguage("Deutsch");
        Thread.sleep(5000);

    }
    @Test
    void testScrollSuggestions() throws InterruptedException {
        {
            home.checkScrollSuggestions();
            Thread.sleep(5000);
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    }


