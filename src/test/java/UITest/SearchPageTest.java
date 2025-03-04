package UITest;

import org.example.DriverFactory;
import org.example.HomePage;
import org.example.SearchPage;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchPageTest {
    WebDriver driver;
    HomePage home;
    SearchPage search;

    private static final String baseURL = "http://localhost:3000";
    private static final String expectedPageTitle = "Search: · Nuxt Movies";
    private static final String expectedPageMessage = "Type something to search...";

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        try {
//            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            //WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
//            //visitSiteButton.click();
//        } catch ( TimeoutException err) {
//            System.out.println("Ngrok warning page was not loaded");
//        }
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        getDriver();
        home = new HomePage(driver);
        home.clickSearch();
        search =new SearchPage(driver);
    }

    @Test
    void testPageTitle() {
        String pageTitle = driver.getTitle();
        assertEquals(expectedPageTitle, pageTitle);
    }

    @Test
    void testPageMessage() throws InterruptedException {
        assertTrue(search.pageMessage());
    }

    @Test
    void testSearchResult(){
        assertTrue(search.searchResult());
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
