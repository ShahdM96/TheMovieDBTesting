package UITest;

import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {
    private WebDriver driver;
    HomePage home;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            //WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            //visitSiteButton.click();
        } catch ( TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
//        driver = DriverFactory.getDriver();
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

    }

//    @Test
//    void testChangeLanguage() throws InterruptedException {
//        String language = "Español";
//        boolean bool = home.changeLanguage(language);
//        Thread.sleep(5000);
//
//
//        assertTrue(bool);
//    }
//
//    @Test
//    void testScrollSuggestions() throws InterruptedException {
//            boolean display = home.scrollToElement();
//            assertTrue(display);
//    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    }


