import org.example.MoviePage;
import org.example.SearchPage;
import org.example.TVShowsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.example.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;
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

    }


    @Test
    void testChangeLanguage() throws InterruptedException {
        String language = "Español";
        boolean bool = home.changeLanguage(language);
        Thread.sleep(5000);


        assertTrue(bool);
    }


    @Test
    void testScrollSuggestions() throws InterruptedException {
            boolean display = home.scrollToElement();
            assertTrue(display);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    }


