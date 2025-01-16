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
    //private static final String baseURL ="http://localhost:3000";
    HomePage home;



    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        home = new HomePage(driver);
    }

    @Test
    void testMoviesButton() throws InterruptedException {
        home.clickMovies();
        Thread.sleep(5000);
    }


    @Test
    void testTVShowsButton() throws InterruptedException {
        home.clickTvShows();
        Thread.sleep(5000);

    }

    @Test
    void testSearchButton() throws InterruptedException {
        home.clickSearch();
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
    @Test
    void testPageIsLoaded() {
        assertTrue(home.isPageLoaded());

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    }


